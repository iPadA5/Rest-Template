package org.example.service.interfaces;

import org.example.model.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<UserModel> getUsers();

    ResponseEntity<String> addUser(UserModel userModel);

    ResponseEntity<String> updateUser(UserModel userModel);

    ResponseEntity<String> deleteUser(Long userId);
}
