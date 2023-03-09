package com.hoxton.databaseconnectionweb.service;

import com.hoxton.databaseconnectionweb.model.vo.DatabaseStatusVO;
import com.hoxton.databaseconnectionweb.request.DatabaseRequest;
import com.hoxton.databaseconnectionweb.request.QueryRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Hoxton on 2023/3/8
 * @since 1.2.0
 */
public interface DatabaseService {

    public String query(QueryRequest queryRequest) throws SQLException, JsonProcessingException, ClassNotFoundException;


    List<DatabaseStatusVO> getDatabaseStatus(DatabaseRequest databaseNameRequest) throws SQLException, ClassNotFoundException;
}
