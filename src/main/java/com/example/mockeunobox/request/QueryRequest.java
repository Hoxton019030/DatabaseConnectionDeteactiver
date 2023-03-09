package com.example.mockeunobox.request;

import lombok.Data;

@Data
public class QueryRequest {
    private final String query;
    private final String databaseName;

}
