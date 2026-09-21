package com.example.banking_api.service;

import com.example.banking_api.entity.Account;
import com.example.banking_api.entity.Transaction;
import com.example.banking_api.enums.TransactionType;
import com.example.banking_api.exception.AccountNotFoundException;
import com.example.banking_api.exception.InsufficientBalanceException;
import com.example.banking_api.repository.AccountRepository;
import com.example.banking_api.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Integer id) {
        return accountRepository.findById(id).orElseThrow(()->new AccountNotFoundException("Account not found with id"+id));
    }

    @Override
    public void deleteById(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Account deposit(Integer id, BigDecimal amount) {
        if(amount.compareTo(BigDecimal.ZERO)<0){
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
     Account account = findById(id);
     account.setBalance(account.getBalance().add(amount));
        Transaction transaction = new Transaction(amount, LocalDateTime.now(), TransactionType.DEPOSIT);
        transaction.setAccount(account);
        accountRepository.save(account);
        transactionRepository.save(transaction);





        return account;
    }


    @Transactional
    public Account withdraw(Integer id, BigDecimal amount) {
        if(amount.compareTo(BigDecimal.ZERO)<0){
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        Account account = findById(id);
        if(account.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("Insufficient BALANCE");
        }
        account.setBalance(account.getBalance().subtract(amount));
        Transaction transaction = new Transaction(amount, LocalDateTime.now(), TransactionType.WITHDRAW);
        transaction.setAccount(account);
        accountRepository.save(account);
        transactionRepository.save(transaction);
        return account  ;
    }


@Transactional
    public Account transfer(Integer fromCountId,Integer toAccountId, BigDecimal amount ) {
        if(amount.compareTo(BigDecimal.ZERO)<0){
            throw new IllegalArgumentException("Amount must be greater than zero");

        }
        Account fromAccount = findById(fromCountId);
        Account toAccount = findById(toAccountId);
        if(fromAccount.equals(toAccount)){
            throw new RuntimeException("Cannot transfer to same account");


        }
        if(fromAccount.getBalance().compareTo(amount)<0){
            throw new InsufficientBalanceException("Insufficient BALANCE");
        }
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));
        Transaction withDrawTransaction = new Transaction(amount, LocalDateTime.now(), TransactionType.TRANSFER);
        withDrawTransaction.setAccount(fromAccount);
        Transaction depositTransaction = new Transaction(amount, LocalDateTime.now(), TransactionType.TRANSFER);
        depositTransaction.setAccount(toAccount);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        transactionRepository.save(withDrawTransaction);
        transactionRepository.save(depositTransaction);
        return fromAccount;







          }
}
