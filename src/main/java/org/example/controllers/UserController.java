package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.model.UserModel;
import org.example.model.request.RequestDto;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserModel> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public String getUsers(@RequestBody RequestDto requestDto) {
        return userService.addUser(requestDto);
    }
}
