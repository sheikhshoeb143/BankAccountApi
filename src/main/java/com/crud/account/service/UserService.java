package com.crud.account.service;


import com.crud.account.error.RecordNotFoundException;
import com.crud.account.model.User;
import com.crud.account.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repo;

    public List<User> findAll() {
        return repo.findAll();
    }

    public User save(User user) {
        String accountNumber = user.getAccountNumber();
        if(accountNumber.length() < 10 || accountNumber.length() > 12){
            System.out.println("Account Number Should be between 10 to 12");
        }
        return repo.save(user);
    }

//    private String validateAccount(String accountNumber) {
//        if((accountNumber != null && accountNumber !="") || (accountNumber.length() < 10 && accountNumber.length() > 12)){
//            return "Account Number Should be between 10 to 12";
//        }
//        else{
//            return "User Added";
//        }
//    }

    public Optional<User> findById(Integer id) {
        return repo.findById(id);
    }

    public boolean checkUserExists(Integer id) {
        return repo.existsById(id);
    }

    public User updateUserById(User user, Integer id) {
        User updateUser = new User();
        updateUser.setId(id);
        updateUser.setFullName(user.getFullName());
        updateUser.setState(user.getState());
        updateUser.setBankName(user.getBankName());
        updateUser.setIfscCode(user.getIfscCode());
        updateUser.setAccountType(user.getAccountType());
        updateUser.setAccountNumber(user.getAccountNumber());
        updateUser.setAmount(user.getAmount());
        return repo.save(updateUser);
    }


    public boolean deleteUserById(Integer id) {

        repo.deleteById(id);

        return true;
    }

    public String deleteAll() {
        repo.deleteAll();
        return "All records deleted";
    }
}
