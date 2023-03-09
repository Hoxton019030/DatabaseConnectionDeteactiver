package com.hoxton.databaseconnectionweb.service;

import com.hoxton.databaseconnectionweb.dao.DatabaseDao;
import com.hoxton.databaseconnectionweb.dao.MSDaoImpl;
import com.hoxton.databaseconnectionweb.dao.PostgresDaoImpl;
import com.hoxton.databaseconnectionweb.exception.DatabaseNotFoundException;
import com.hoxton.databaseconnectionweb.request.QueryRequest;
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
        return databaseDao.connect().query(queryRequest.getQuery());
    }

    private DatabaseDao getDatabaseDao(String databaseName) {
        switch (databaseName){
            case "Postgres":
                return new PostgresDaoImpl();
            case  "MsSql":
                return new MSDaoImpl();
            default:
                throw DatabaseNotFoundException.createDatabaseNotFoundException("Not this Database");
        }

    }
}
