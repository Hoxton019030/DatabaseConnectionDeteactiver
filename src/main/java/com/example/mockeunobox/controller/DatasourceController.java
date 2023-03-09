package com.example.mockeunobox.controller;

import com.example.mockeunobox.request.QueryRequest;
import com.example.mockeunobox.service.DatabaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

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
    @GetMapping("/test")
    public String query(QueryRequest queryRequest) throws SQLException, JsonProcessingException, ClassNotFoundException {
        return databaseService.query(queryRequest);
    }

}
