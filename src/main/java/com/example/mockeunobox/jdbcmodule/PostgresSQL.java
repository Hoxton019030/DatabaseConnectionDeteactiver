package com.example.mockeunobox.jdbcmodule;

import com.example.mockeunobox.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hoxton on 2023/3/7
 * @since 1.2.0
 */
public class PostgresSQL implements DatabaseModule {
    private Connection connection = null;

    @Override
    public void connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/school";
        String user = "postgres";
        String password = "45002502";
        connection = DriverManager.getConnection(url, user, password);
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public List<User> query() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from users;");
        ResultSet resultSet = statement.executeQuery();
        List<User> userList = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            int age = resultSet.getInt("age");
            String name = resultSet.getString("name");
            String birthdate = resultSet.getString("birthdate");
            User user = User.builder().id(id).name(name).age(age).birthdate(birthdate).build();
            userList.add(user);
        }
        return userList;
    }

    @Override
    public String getStatOfDatabase() {
        return null;
    }
}
