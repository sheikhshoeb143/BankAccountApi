package com.crud.account.controller;

import com.crud.account.error.RecordNotFoundException;
import com.crud.account.model.User;
import com.crud.account.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserService service;

    // Get all users
    // http://localhost:8080/user
    @GetMapping("/user")
    public List<User> getAllUsers() {
        return service.findAll();

    }

    // Add Users
    // http://localhost:8080/add-user
//    @PostMapping("/add-user")
//    public ResponseEntity<User> addUser(@RequestBody @Valid User user) {
//        System.out.println("User Added");
//        User newUser = service.save(user);
//        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
//    }


    @PostMapping("/add-user")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user) {
        System.out.println("User Added");
        if (!service.isValidBankNo(user.getAccountNumber())) {
            return new ResponseEntity<String>("Please Enter Valid Account Number", HttpStatus.NOT_FOUND);
        }else {
            User newUser = service.save(user);
            return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
        }
    }

    // GET Single User
    // http://localhost:8080/user/1
    @GetMapping(path = "/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Integer id) {
        Optional<User> user = service.findById(id);
        if (!user.isPresent()) {
            throw new RecordNotFoundException("User " + id + " not found.");
        }

        return new ResponseEntity<User>(user.get(), HttpStatus.FOUND);
    }


    // Update User
    // http://localhost:8080/update-user/{id}
    @PutMapping(path = "/update-user/{id}")
    public User updateUser(@RequestBody @Valid User user, @PathVariable("id") Integer id) {
        return service.updateUserById(user, id);
    }

    // Patch - partial update specific column
    // http://localhost:8080/patch-user/{id}
    @PatchMapping(path = "/patch-user/{id}")
    public ResponseEntity<User> updateUserAmountorName(@RequestBody @Valid User user, @PathVariable("id") Integer id) {

        Optional<User> currentUser = service.findById(id);
        User updateUserData = new User();
        if (!currentUser.isPresent()) {
            throw new RecordNotFoundException("User " + id + " not found.");
        } else {
            updateUserData = currentUser.get();
            if (user.getFullName() != null && user.getFullName() != "") {
                updateUserData.setFullName(user.getFullName());
            }
            if (user.getState() != null && user.getState() != "") {
                updateUserData.setState(user.getState());
            }
            if (user.getBankName() != null && user.getBankName() != "") {
                updateUserData.setBankName(user.getBankName());
            }
            if (user.getIfscCode() != null && user.getIfscCode() != "") {
                updateUserData.setIfscCode(user.getIfscCode());
            }
            if (user.getAccountType() != null && user.getAccountType() != "") {
                updateUserData.setAccountType(user.getAccountType());
            }
            if (user.getAccountNumber() != null && user.getAccountNumber() != "") {
                updateUserData.setAccountNumber(user.getAccountNumber());
            }
            if (user.getAmount() != null && user.getAmount() != "") {
                updateUserData.setAmount(user.getAmount());
            }
            return new ResponseEntity<User>(service.save(updateUserData), HttpStatus.OK);
        }
    }

    // Delete Single User
    // http://localhost:8080/delete-user/1
    @DeleteMapping(path = "/delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {
        boolean isUserExists = service.checkUserExists(id);

        if (!isUserExists) {
            throw new RecordNotFoundException("User " + id + " not found.");
        } else {
            service.deleteUserById(id);
        }

        return new ResponseEntity<String>("User " + id + " deleted successfully", HttpStatus.OK);

    }


    // Delete All Users
    // http://localhost:8080/delete-all-users
    @DeleteMapping("/delete-all-users")
    public String deleteAll() {
        return service.deleteAll();
    }
}