package com.example.banking_api.service;

import com.example.banking_api.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService  {
    Account save(Account account);
    List<Account> findAll();
    Account findById(Integer id);
    void deleteById(Integer id);
    Account deposit(Integer id, BigDecimal amount);
    Account withdraw(Integer accountId, BigDecimal amount);
    Account transfer(Integer fromAccountId,
                     Integer toAccountId,
                     BigDecimal amount);




}
