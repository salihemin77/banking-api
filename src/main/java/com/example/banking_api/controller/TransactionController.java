package com.example.banking_api.controller;

import com.example.banking_api.dto.TransactionDTO;
import com.example.banking_api.entity.Transaction;
import com.example.banking_api.mapper.TransactionMapper;
import com.example.banking_api.service.AccountService;
import com.example.banking_api.service.TransactionService;
import com.example.banking_api.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping("/api")
public class TransactionController {
    private TransactionService transactionService;
    private TransactionMapper transactionMapper;

    public TransactionController(TransactionMapper transactionMapper, TransactionService transactionService) {
        this.transactionMapper = transactionMapper;
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<TransactionDTO> getTransactions(){
        return transactionService.findAll().stream().map(transactionMapper::toDTO).collect(toList());
    }
    @GetMapping("/transactions/{id}")
    public TransactionDTO getTransaction(@PathVariable Integer id){
        Transaction transaction = transactionService.findById(id);
        return transactionMapper.toDTO(transaction);

    }

    @PostMapping("/transactions")

    public TransactionDTO createTransaction(@RequestBody Transaction transaction){
        Transaction transaction1=transactionService.save(transaction);
        return transactionMapper.toDTO(transaction1);
    }

}
