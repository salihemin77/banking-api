package com.example.banking_api.service;

import com.example.banking_api.entity.Transaction;
import com.example.banking_api.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TransactionImpl implements TransactionService {
    private TransactionRepository transactionRepository;

    public TransactionImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction findById(Integer id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        transactionRepository.deleteById(id);
    }
}
