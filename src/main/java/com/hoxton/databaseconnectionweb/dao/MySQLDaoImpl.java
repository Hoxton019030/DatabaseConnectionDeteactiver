package com.hoxton.databaseconnectionweb.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hoxton.databaseconnectionweb.model.vo.DatabaseStatusVO;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Hoxton on 2023/3/8
 * @since 1.2.0
 */
@Repository

public class MySQLDaoImpl implements DatabaseDao {
    String url = "jdbc:mysql://localhost:3306";
    String username = "root";



    @Override
    public DatabaseDao connect() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public String query(String query) throws SQLException, JsonProcessingException {
        return null;
    }

    @Override
    public List<DatabaseStatusVO> getDatabaseStatus(String databaseName) {
        return null;
    }
}
