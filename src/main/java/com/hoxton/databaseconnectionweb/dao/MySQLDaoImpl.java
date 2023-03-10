package com.hoxton.databaseconnectionweb.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoxton.databaseconnectionweb.model.vo.DatabaseStatusVO;
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
public class MySQLDaoImpl implements DatabaseEngineDao {
    String url = "jdbc:mysql://localhost:3306/school";
    String username = "root";
    String password = "root";
    private String databaseEngineName="MySql";
    Connection connection;

    @Override
    public DatabaseEngineDao connect() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        return this;
    }

    @Override
    public void close() throws SQLException {

    }

    @Override
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
    public List<DatabaseStatusVO> getDatabaseStatus(String databaseName) {
        return null;
    }

    @Override
    public String getDatabaseEngineName() {
        return databaseEngineName;
    }
}
