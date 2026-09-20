package com.example.banking_api.service;

import com.example.banking_api.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction findById(Integer id);
    Transaction save(Transaction transaction);
    List<Transaction> findAll();
    void deleteById(Integer id);

}
