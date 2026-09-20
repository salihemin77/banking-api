package com.example.banking_api.mapper;

import com.example.banking_api.dto.TransactionDTO;
import com.example.banking_api.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionDTO toDTO(Transaction transaction) {
        return new TransactionDTO(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getDate(),
                transaction.getType(),
                transaction.getAccount().getId()
        );
    }

}
