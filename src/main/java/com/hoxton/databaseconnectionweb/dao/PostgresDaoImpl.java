package com.hoxton.databaseconnectionweb.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoxton.databaseconnectionweb.model.vo.DatabaseStatusVO;
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
    public List<DatabaseStatusVO> getDatabaseStatus(String databaseName) throws SQLException {
        List<DatabaseStatusVO> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(databaseStatusQuerySyntax)) {
            preparedStatement.setString(1, databaseName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String dbname = resultSet.getString("datname");
                Integer pid = resultSet.getInt("pid");
                String userName = resultSet.getString("usename");
                String applicationName = resultSet.getString("application_name");
                String clientAddress = resultSet.getString("client_addr");
                String clientHostname = resultSet.getString("client_hostname");
                Integer clientPort = resultSet.getInt("client_port");
                Timestamp backendStart = resultSet.getTimestamp("backend_start");
                Timestamp xactStart = resultSet.getTimestamp("xact_start");
                Timestamp queryStart = resultSet.getTimestamp("query_start");
                Timestamp stateChange = resultSet.getTimestamp("state_change");
                String waitEventType = resultSet.getString("wait_event_type");
                String waitEvent = resultSet.getString("wait_event");
                String state = resultSet.getString("state");
                Integer backendXid = resultSet.getInt("backend_xid");
                Integer backendXmin = resultSet.getInt("backend_Xmin");
                String query = resultSet.getString("query");
                String backendType = resultSet.getString("backend_type");


                PSQLDatabaseStatusVO psqlDatabaseStatusVO = PSQLDatabaseStatusVO.builder()
                        .databaseName(dbname)
                        .pid(pid)
                        .username(userName)
                        .applicationName(applicationName)
                        .clientAddress(clientAddress)
                        .clientHostname(clientHostname)
                        .clientPort(clientPort)
                        .backendStart(backendStart)
                        .xactStart(xactStart)
                        .queryStart(queryStart)
                        .stateChange(stateChange)
                        .waitEventType(waitEventType)
                        .waitEvent(waitEvent)
                        .state(state)
                        .backendXid(backendXid)
                        .backendXmin(backendXmin)
                        .query(query)
                        .backendType(backendType).build();
                result.add(psqlDatabaseStatusVO);
            }
        }


        return result;
    }
}
