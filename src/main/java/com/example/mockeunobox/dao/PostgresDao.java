package com.example.mockeunobox.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class PostgresDao implements DatabaseDao{
    String url;
    String username;
    String password;

    Connection connection;

    @Override
    public void connect() throws SQLException, ClassNotFoundException {
      connection = DriverManager.getConnection(url, username, password);
    }

    @Override
    public void close() throws SQLException {
        if(connection !=null){
            connection.close();
        }

    }


    public String query(String query) throws SQLException, JsonProcessingException {
        List<Map<String,Object>> result = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while(resultSet.next()){
                HashMap<String, Object> row = new HashMap<>();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for(int i=1;i<=columnCount;i++){
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(columnName);
                    row.put(columnName, value);
                }
                result.add(row);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(result);
    }
}
