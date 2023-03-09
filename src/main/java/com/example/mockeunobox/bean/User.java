package com.example.mockeunobox.bean;

import lombok.Builder;
import lombok.Data;

/**
 * @author Hoxton on 2023/3/8
 * @since 1.2.0
 */
@Data
@Builder
public class User {
    Integer id;
    String name;
    Integer age;
    String birthdate;
}
