package com.example.mockeunobox.jdbcmodule;

import com.example.mockeunobox.bean.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Hoxton on 2023/3/7
 * @since 1.2.0
 */
public interface DatabaseModule {
    void connect() throws SQLException, ClassNotFoundException;
    void close() throws SQLException;
    List<User> query() throws SQLException;
    String getStatOfDatabase();
}
