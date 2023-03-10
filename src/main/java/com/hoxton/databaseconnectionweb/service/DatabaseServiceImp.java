package com.hoxton.databaseconnectionweb.service;

import com.hoxton.databaseconnectionweb.dao.DatabaseDao;
import com.hoxton.databaseconnectionweb.dao.MySQLDaoImpl;
import com.hoxton.databaseconnectionweb.dao.PostgresDaoImpl;
import com.hoxton.databaseconnectionweb.exception.DatabaseNotFoundException;
import com.hoxton.databaseconnectionweb.model.vo.DatabaseStatusVO;
import com.hoxton.databaseconnectionweb.request.DatabaseRequest;
import com.hoxton.databaseconnectionweb.request.QueryRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

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
        String databaseName = queryRequest.getDatabaseEngine();
        databaseDao = getDatabaseDao(databaseName);
        return databaseDao.connect().query(queryRequest.getQuery());
    }
    @Override
    public List<DatabaseStatusVO> getDatabaseStatus(DatabaseRequest databaseRequest) throws SQLException, ClassNotFoundException {
        databaseDao = getDatabaseDao(databaseRequest.getDatabaseEngine());
        return databaseDao.connect().getDatabaseStatus(databaseRequest.getDatabaseName());
    }

    private DatabaseDao getDatabaseDao(String databaseName) {
        switch (databaseName){
            case "Postgres":
                return new PostgresDaoImpl();
            case  "MsSql":
                return new MySQLDaoImpl();
            default:
                throw DatabaseNotFoundException.createDatabaseNotFoundException("Not this Database");
        }

    }
}
