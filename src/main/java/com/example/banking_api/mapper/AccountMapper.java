package com.example.banking_api.mapper;

import com.example.banking_api.dto.AccountDTO;
import com.example.banking_api.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public AccountDTO toDTO(Account account) {
        return new AccountDTO(
                account.getId(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getAccountType(),
                account.getUser().getId()
        );
    }
}
