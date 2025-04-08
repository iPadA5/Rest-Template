package org.example.model;

import lombok.Data;

@Data
public class UserModel {
    private Long id;
    private String name;
    private String lastName;
    private Byte age;
}
