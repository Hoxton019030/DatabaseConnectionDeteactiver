package com.example.mockeunobox.service;

import lombok.Data;

/**
 * @author Hoxton on 2023/3/8
 * @since 1.2.0
 */
@Data
public class DatabaseManager {

    String categlory;

    public DatabaseManager(String categlory) {
        this.categlory = categlory;
    }


}
