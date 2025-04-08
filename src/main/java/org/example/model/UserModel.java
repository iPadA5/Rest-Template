package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserModel {
    private Long id;
    private String name;
    private String lastName;
    private Byte age;
}
