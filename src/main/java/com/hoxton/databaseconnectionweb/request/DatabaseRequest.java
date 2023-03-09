package com.hoxton.databaseconnectionweb.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Hoxton on 2023/3/9
 * @since 1.2.0
 */

@Getter
@Setter
public class DatabaseRequest extends Request{
    String databaseEngine;
    String databaseName;

}
