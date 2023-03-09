package com.example.mockeunobox.exception;

import lombok.Data;

/**
 * @author Hoxton on 2023/3/9
 * @since 1.2.0
 */
@Data
public class DatabaseNotFoundException extends BaseException{

    private DatabaseNotFoundException(String errorMsg){
        super(errorMsg);
    }

    public static DatabaseNotFoundException  createDatabaseNotFoundException(String errorMsg){
        return new DatabaseNotFoundException(errorMsg);

    }

}
