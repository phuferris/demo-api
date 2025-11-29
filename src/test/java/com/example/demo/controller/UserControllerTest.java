package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerTest {
    UserService userService = Mockito.mock(UserService.class);
    UserController userController = new UserController(userService);

    @Test
    public void testFindUserById() {
        long userId = 100;
        User user = new User(
                "Phu Nguyen",
                "pnguyen@gmail.com"
        );
        user.setId(userId);
        Mockito.when(userService.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
        ResponseEntity<User> response = userController.getUserById((long)userId);
        Mockito.verify(userService).findById((long)userId);
        assertEquals(
                response.getBody().getEmail(),
                user.getEmail()
        );
        assertEquals(
                userId,
                response.getBody().getId()
        );

    }

    @Test
    public void testCreateUser() {
        long userId = 100;
        User user = new User(
                "Phu Nguyen",
                "pnguyen@gmail.com"
        );
        user.setId(userId);
        Mockito.when(userService.saveUser(Mockito.any(User.class))).thenReturn(user);
    }
}
