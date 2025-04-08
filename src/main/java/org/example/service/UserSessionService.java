package org.example.service;

import lombok.AllArgsConstructor;
import org.example.config.SessionInit;
import org.example.model.UserModel;
import org.example.service.interfaces.UserService;
import org.example.service.interfaces.UserSessionServiceInterface;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserSessionService implements UserSessionServiceInterface {
    private final UserService userService;
    private final SessionInit sessionInit;

    @Override
    public void perfomAllOperations() {
        StringBuilder parts = new StringBuilder();
        sessionInit.initialize();
        userService.getUsers();

        UserModel addedUser = new UserModel(3L, "James", "Brown", (byte)5);
         parts.append(userService.addUser(addedUser).getBody());

        UserModel updatedUser = new UserModel(3L, "Thomas", "Shelby", (byte)5);
        parts.append(userService.updateUser(updatedUser).getBody());
        parts.append( userService.deleteUser(3L).getBody());
        System.out.println(parts);
    }
}
