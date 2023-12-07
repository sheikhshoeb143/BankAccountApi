package com.crud.account.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="user_data")
public class User {
    @Id
    @NotNull(message = "Id should not be null")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    @NotNull(message = "Full Name should not be null")
    private String fullName;

    @Column
    @NotNull(message = "State should not be null")
    private String state;

    @Column
    @NotNull(message = "Bank Name should not be null")
    private String bankName;

    @Column
    @NotNull(message = "IFSC Code should not be null")
    private String ifscCode;

    @Column
    @NotNull(message = "Account Type should not be null")
    private String accountType;

    @Column
    @NotNull(message = "Account Number should not be null")
    private String accountNumber;

    @Column
    @NotNull(message = "Amount should not be null")
    private String amount;

    public User() {
        super();
    }

    public User(int id, String fullName, String state, String bankName, String ifscCode, String accountType, String accountNumber, String amount) {
        this.id = id;
        this.fullName = fullName;
        this.state = state;
        this.bankName = bankName;
        this.ifscCode = ifscCode;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
