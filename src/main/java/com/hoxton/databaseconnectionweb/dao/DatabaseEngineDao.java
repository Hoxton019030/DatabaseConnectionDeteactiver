package com.hoxton.databaseconnectionweb.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hoxton.databaseconnectionweb.model.vo.DatabaseStatusVO;
import org.springframework.stereotype.Repository;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Hoxton on 2023/3/8
 * @since 1.2.0
 */
@Repository
public interface DatabaseEngineDao {


    DatabaseEngineDao connect() throws SQLException;
    void close() throws SQLException;

    String query(String query) throws SQLException, JsonProcessingException;
    List<DatabaseStatusVO> getDatabaseStatus(String databaseName) throws SQLException;

    public String getDatabaseEngineName();



}
