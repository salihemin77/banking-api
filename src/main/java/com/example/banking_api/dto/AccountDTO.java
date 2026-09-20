package com.example.banking_api.dto;

import com.example.banking_api.enums.AccountType;

import java.math.BigDecimal;

public class AccountDTO {
    private Integer id;
    private String accountNumber;
    private BigDecimal balance;
    private AccountType accountType;
    private Integer userId;

    public AccountDTO() {
    }

    public AccountDTO(Integer id, String accountNumber, BigDecimal balance,
                      AccountType accountType, Integer userId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
