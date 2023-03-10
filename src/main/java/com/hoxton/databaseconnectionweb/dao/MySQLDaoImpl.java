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
public class MySQLDaoImpl implements DatabaseEngineDao {
    String url = "jdbc:mysql://localhost:3306";
    String username = "root";
    private String databaseEngineName;


    @Override
    public DatabaseEngineDao connect() throws SQLException {
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

    @Override
    public String getDatabaseEngineName() {
        return databaseEngineName;
    }
}
