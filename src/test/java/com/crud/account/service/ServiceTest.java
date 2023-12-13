package com.crud.account.service;

import com.crud.account.model.User;
import com.crud.account.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ServiceTest {
    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;



    @Test
    void contextLoads() {
    }

    @Test
    public void findAllTest(){
        when(repository.findAll()).thenReturn(Stream.of(new User(1, "Sheikh Shoeb", "KA", "SBI", "SBIN0007065", "Current", "20288091922", "6000"), new User(2, "Ashu", "CG", "ICICI", "ICIC07065", "Savings", "70288091922", "60000")).collect(Collectors.toList()));
        assertEquals(2, service.findAll().size());
    }



    @Test
    public void saveTest(){
        User user = new User(1, "Sheikh Shoeb", "KA", "SBI", "SBIN0007065", "Current", "20288091922", "6000");
        when(repository.save(user)).thenReturn(user);
        assertEquals(user, service.save(user));
    }

    @Test
    public void findByIdTest(){
        int userId = 1;
        User user = new User(1, "Sheikh Shoeb", "KA", "SBI", "SBIN0007065", "Current", "20288091922", "6000");
        // Mocking the behavior of the repository
        when(repository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = service.findById(userId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getId());
        assertEquals("Sheikh Shoeb", result.get().getFullName());

        // Verify that the repository's findById method was called with the correct argument
        verify(repository, times(1)).findById(userId);
    }

    @Test
    public void findByIdTest2(){
        int id = 1;
        User user =	new User(1, "Sheikh Shoeb", "KA", "SBI", "SBIN0007065", "Current", "20288091922", "6000");
        when(repository.findById(id)).thenReturn(Optional.of(user));
        assertEquals(Optional.of(user), service.findById(id));
    }

    @Test
    public void checkUserExistsTest(){
        // Arrange
        int userId = 1;

        // Mocking the behavior of the repository
        when(repository.existsById(userId)).thenReturn(true);

        // Act
        boolean result = service.checkUserExists(userId);

        // Assert
        assertTrue(result);

        // Verify that the repository's existsById method was called with the correct argument
        verify(repository, times(1)).existsById(userId);
    }

    //IsValidBank
    @Test
    public void isValidBankNoTest(){
        UserService userService = new UserService(); // Assuming your service class is named UserService

        // Valid bank account numbers
        String validBankAccNo1 = "1234567890";
        String validBankAccNo2 = "9876543210";

        // Invalid bank account numbers
        String invalidBankAccNo1 = "12345";
        String invalidBankAccNo2 = "abcdefghij";

        // Act and Assert
        assertTrue(userService.isValidBankNo(validBankAccNo1));
        assertTrue(userService.isValidBankNo(validBankAccNo2));

        assertFalse(userService.isValidBankNo(invalidBankAccNo1));
        assertFalse(userService.isValidBankNo(invalidBankAccNo2));
    }


    //Update
    @Test
    public void updateUserByIdTest(){
        // Arrange
        int userId = 1;
        User userToUpdate = new User(1, "Sheikh Shoeb", "KA", "SBI", "SBIN0007065", "Current", "20288091922", "6000");
        userToUpdate.setFullName("Sheikh Shoeb");
        userToUpdate.setState("Karnataka");
        userToUpdate.setBankName("HDFC");
        userToUpdate.setIfscCode("HDFC0007065");
        userToUpdate.setAccountType("Savings");
        userToUpdate.setAccountNumber("1234567890");
        userToUpdate.setAmount("1000");

        // Mocking the behavior of the repository
        when(repository.save(any(User.class))).thenReturn(userToUpdate);

        // Act
        User result = service.updateUserById(userToUpdate, userId);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("Sheikh Shoeb", result.getFullName());
        assertEquals("Karnataka", result.getState());
        assertEquals("HDFC", result.getBankName());
        assertEquals("HDFC0007065", result.getIfscCode());
        assertEquals("Savings", result.getAccountType());
        assertEquals("1234567890", result.getAccountNumber());
        assertEquals("1000", result.getAmount());

        // Verify that the repository's save method was called with the correct argument
        verify(repository, times(1)).save(any(User.class));
    }

    //delete by ID
    @Test
    public void deleteUserByIdTest(){
        // Arrange
        int userId = 1;

        // Mocking the behavior of the repository
        doNothing().when(repository).deleteById(userId);

        // Act
        boolean result = service.deleteUserById(userId);

        // Assert
        assertTrue(result);

        // Verify that the repository's deleteById method was called with the correct argument
        verify(repository, times(1)).deleteById(userId);
    }

    //Delete All
    @Test
    public void deleteAllTest(){
        doNothing().when(repository).deleteAll();

        // Act
        String result = service.deleteAll();

        // Assert
        assertEquals("All records deleted", result);

        // Verify that the repository's deleteAll method was called
        verify(repository, times(1)).deleteAll();
    }
}
