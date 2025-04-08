package org.example.service;

import org.example.model.UserModel;
import org.example.model.request.RequestDto;

import java.util.List;

public interface UserService {

    List<UserModel> getUsers();

    String addUser(RequestDto requestDto);

}
