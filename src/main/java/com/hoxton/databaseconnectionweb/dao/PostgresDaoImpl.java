package com.hoxton.databaseconnectionweb.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoxton.databaseconnectionweb.model.vo.PSQLDatabaseStatusVO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hoxton on 2023/3/8
 * @since 1.2.0
 */
@Repository
@Primary
public class PostgresDaoImpl implements DatabaseDao {
    String url = "jdbc:postgresql://localhost/school";
    String username = "postgres";
    String password = "45002502";

    String databaseStatusQuerySyntax = "SELECT * FROM pg_stat_activity WHERE datname = ?";
    Connection connection;

    @Override
    public DatabaseDao connect() throws SQLException, ClassNotFoundException {
        connection = DriverManager.getConnection(url, username, password);
        return this;
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }

    }


    public String query(String query) throws SQLException, JsonProcessingException {
        List<Map<String, Object>> result = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                HashMap<String, Object> row = new HashMap<>();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(columnName);
                    row.put(columnName, value);
                }
                result.add(row);
            }
        }

        return mapper.writeValueAsString(result);
    }

    @Override
    public PSQLDatabaseStatusVO getDatabaseStatus(String databaseName) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(databaseStatusQuerySyntax)){
            preparedStatement.setString(1, databaseName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String dbName = resultSet.getString("database_name");
                int databaseSizeMb = resultSet.getInt("database_size_mb");
                String databaseSizePretty = resultSet.getString("database_size_pretty");
                int numBackends = resultSet.getInt("num_backends");
                int transactionsCommitted = resultSet.getInt("transactions_committed");
                int transactionsRolledBack = resultSet.getInt("transactions_rolled_back");

                PSQLDatabaseStatusVO.builder()
                        .databaseName(databaseName)
                        .databaseSizeMb(databaseSizeMb)
                        .databaseSizePretty(databaseSizePretty)
                        .deadlock()
            }
        }


        return null;
    }
}
