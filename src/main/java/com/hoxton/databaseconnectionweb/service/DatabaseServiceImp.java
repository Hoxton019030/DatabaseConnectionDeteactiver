package com.hoxton.databaseconnectionweb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hoxton.databaseconnectionweb.dao.DatabaseEngineDao;
import com.hoxton.databaseconnectionweb.exception.DatabaseNotFoundException;
import com.hoxton.databaseconnectionweb.model.vo.DatabaseStatusVO;
import com.hoxton.databaseconnectionweb.request.DatabaseRequest;
import com.hoxton.databaseconnectionweb.request.QueryRequest;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Hoxton on 2023/3/8
 * @since 1.2.0
 */
@Service
public class DatabaseServiceImp implements DatabaseService {
    DatabaseEngineDao databaseEngineDao;
    List<DatabaseEngineDao> databaseDaoList;


    public DatabaseServiceImp(List<DatabaseEngineDao> databaseDaoList) {
        this.databaseDaoList = databaseDaoList;
    }

    @Override
    public String query(QueryRequest queryRequest) throws SQLException, JsonProcessingException, ClassNotFoundException {
        String databaseName = queryRequest.getDatabaseEngine();
        databaseEngineDao = getDatabaseDao(databaseName);
        return databaseEngineDao.connect().query(queryRequest.getQuery());
    }

    @Override
    public List<DatabaseStatusVO> getDatabaseStatus(DatabaseRequest databaseRequest) throws SQLException, ClassNotFoundException {
        databaseEngineDao = getDatabaseDao(databaseRequest.getDatabaseEngine());
        return databaseEngineDao.connect().getDatabaseStatus(databaseRequest.getDatabaseName());
    }

    private DatabaseEngineDao getDatabaseDao(String databaseEngine) {
        for (DatabaseEngineDao databaseDao : databaseDaoList) {
            if (databaseEngine.equals(databaseDao.getDatabaseEngineName())) {
                return databaseDao;
            }
        }
        throw DatabaseNotFoundException.createDatabaseNotFoundException("Not Found This DatabaseEngine，請檢查是否有相應的databaseEngineName在DAO中");
    }
}
