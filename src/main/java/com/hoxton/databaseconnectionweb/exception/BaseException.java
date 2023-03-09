package com.hoxton.databaseconnectionweb.exception;

import lombok.NoArgsConstructor;

/**
 * @author Hoxton on 2023/3/9
 * @since 1.2.0
 */
@NoArgsConstructor
public class BaseException extends RuntimeException{
    private String errorMeg;

    protected BaseException(String errorMeg){
        this.errorMeg=errorMeg;
    }
}
