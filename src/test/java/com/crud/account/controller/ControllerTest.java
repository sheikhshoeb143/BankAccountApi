package com.crud.account.controller;

import com.crud.account.error.RecordNotFoundException;
import com.crud.account.model.User;
import com.crud.account.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetAllUsers() {
        // Arrange
        List<User> users = Arrays.asList(new User(), new User());
        when(userService.findAll()).thenReturn(users);

        // Act
        List<User> result = userController.getAllUsers();

        // Assert
        assertEquals(users, result);
    }

    @Test
    public void testAddUserValid() {
        // Arrange
        User user = new User();
        when(userService.isValidBankNo(any())).thenReturn(true);
        when(userService.save(user)).thenReturn(user);

        // Act
        ResponseEntity<?> result = userController.addUser(user);

        // Assert
        assertEquals(new ResponseEntity<User>(user, HttpStatus.CREATED), result);
    }

    @Test
    public void testAddUserInvalid() {
        // Arrange
        User user = new User();
        when(userService.isValidBankNo(any())).thenReturn(false);

        // Act
        ResponseEntity<?> result = userController.addUser(user);

        // Assert
        assertEquals(new ResponseEntity<String>("Please Enter Valid Account Number", HttpStatus.NOT_FOUND), result);
    }

    @Test
    public void testGetUser() {
        // Arrange
        Integer userId = 1;
        User user = new User();
        when(userService.findById(userId)).thenReturn(Optional.of(user));

        // Act
        ResponseEntity<?> result = userController.getUser(userId);

        // Assert
        assertEquals(new ResponseEntity<User>(user, HttpStatus.FOUND), result);
    }


    @Test
    public void testDeleteAll() {
        // Arrange
        when(userService.deleteAll()).thenReturn("All records deleted");

        // Act
        String result = userController.deleteAll();

        // Assert
        assertEquals("All records deleted", result);
        verify(userService, times(1)).deleteAll();
    }


    //Delete By Id
    @Test
    public void testDeleteUser() {
        // Arrange
        Integer userId = 1;
        when(userService.checkUserExists(userId)).thenReturn(true);

        // Act
        ResponseEntity<String> result = userController.deleteUser(userId);

        // Assert
        assertEquals(new ResponseEntity<String>("User 1 deleted successfully", HttpStatus.OK), result);
        verify(userService, times(1)).checkUserExists(userId);
        verify(userService, times(1)).deleteUserById(userId);
    }

    @Test
    public void testDeleteUserNotFound() {
        // Arrange
        Integer userId = 1;
        when(userService.checkUserExists(userId)).thenReturn(false);

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> {
            userController.deleteUser(userId);
        });

        verify(userService, times(1)).checkUserExists(userId);
        verify(userService, never()).deleteUserById(any());
    }
}
