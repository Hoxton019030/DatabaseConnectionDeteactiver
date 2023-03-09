package com.example.mockeunobox.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * @author Hoxton on 2023/3/8
 * @since 1.2.0
 */
@Repository

public class MSDaoImpl implements DatabaseDao {
    @Override
    public void connect() throws SQLException, ClassNotFoundException {

    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public String query(String query) throws SQLException, JsonProcessingException {
        return null;
    }
}
