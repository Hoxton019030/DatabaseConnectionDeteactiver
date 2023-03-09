package com.hoxton.databaseconnectionweb.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hoxton.databaseconnectionweb.model.vo.DatabaseStatusVO;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * @author Hoxton on 2023/3/8
 * @since 1.2.0
 */
@Repository

public interface DatabaseDao {

    DatabaseDao connect() throws SQLException, ClassNotFoundException;

    void close() throws SQLException;

    String query(String query) throws SQLException, JsonProcessingException;
    DatabaseStatusVO getDatabaseStatus(String databaseName) throws SQLException;




}
