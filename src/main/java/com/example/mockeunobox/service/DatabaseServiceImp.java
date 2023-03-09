package com.example.mockeunobox.service;

import com.example.mockeunobox.dao.DatabaseDao;
import com.example.mockeunobox.dao.MSDaoImpl;
import com.example.mockeunobox.dao.PostgresDao;
import com.example.mockeunobox.exception.DatabaseNotFoundException;
import com.example.mockeunobox.request.QueryRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @author Hoxton on 2023/3/8
 * @since 1.2.0
 */
@Service
public class DatabaseServiceImp implements DatabaseService{

    DatabaseDao databaseDao;

    public DatabaseServiceImp(DatabaseDao databaseDao) {
        this.databaseDao = databaseDao;
    }

    @Override
    public String query(QueryRequest queryRequest) throws SQLException, JsonProcessingException, ClassNotFoundException {
        String databaseName = queryRequest.getDatabaseName();
        databaseDao = getDatabaseDao(databaseName);
        return null;
    }

    private DatabaseDao getDatabaseDao(String databaseName) {
        switch (databaseName){
            case "Postgres":
                return new PostgresDao();
            case  "MsSql":
                return new MSDaoImpl();
            default:
                throw DatabaseNotFoundException.createDatabaseNotFoundException("Not this Database");
        }

    }
}
