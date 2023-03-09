package com.hoxton.databaseconnectionweb.controller;

import com.hoxton.databaseconnectionweb.model.vo.DatabaseStatusVO;
import com.hoxton.databaseconnectionweb.request.DatabaseRequest;
import com.hoxton.databaseconnectionweb.request.QueryRequest;
import com.hoxton.databaseconnectionweb.service.DatabaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Hoxton on 2023/3/7
 * @since 1.2.0
 */
@RestController
public class DatasourceController {

    DatabaseService databaseService;

    public DatasourceController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping("/query")
    public String query(@RequestBody QueryRequest queryRequest) throws SQLException, JsonProcessingException, ClassNotFoundException {
        return databaseService.query(queryRequest);
    }

    @GetMapping("/databaseStatus")
    public List<DatabaseStatusVO> getDatabaseStatus(@RequestBody DatabaseRequest databaseRequest) throws SQLException, ClassNotFoundException {
        return databaseService.getDatabaseStatus(databaseRequest);
    }

}
