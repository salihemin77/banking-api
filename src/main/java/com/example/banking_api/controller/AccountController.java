package com.example.banking_api.controller;

import com.example.banking_api.dto.AccountDTO;
import com.example.banking_api.entity.Account;
import com.example.banking_api.mapper.AccountMapper;
import com.example.banking_api.mapper.UserMapper;
import com.example.banking_api.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class AccountController {
    private AccountService accountService;
  private AccountMapper accountMapper;

    public AccountController(AccountMapper accountMapper, AccountService accountService) {
        this.accountMapper = accountMapper;
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<AccountDTO> findAll(){
        return accountService.findAll().stream().map(accountMapper::toDTO).collect(toList());


    }
    @GetMapping("/accounts/{id}")
    public AccountDTO findById(@PathVariable Integer id){
        Account account = accountService.findById(id);
        return accountMapper.toDTO(account);
    }
    @PostMapping("/accounts")
    public AccountDTO createAccount(@Valid @RequestBody Account account){
        Account account1=accountService.save(account);
        return accountMapper.toDTO(account1);

    }
    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable Integer id){
        accountService.deleteById(id);

    }
    @PostMapping("/accounts/{id}/deposit")
    public AccountDTO deposit(@PathVariable Integer id,  @RequestBody BigDecimal amount ){
     Account account=accountService.deposit(id, amount);
     return accountMapper.toDTO(account);
    }
    @PostMapping("/accounts/{id}/withdraw")
    public AccountDTO withdraw(@PathVariable Integer id,  @RequestBody BigDecimal amount ){
        Account account=accountService.withdraw(id, amount);
        return accountMapper.toDTO(account);
    }


        // mevcut metodların burada

        @PostMapping("/accounts/{fromAccountId}/transfer/{toAccountId}")
        public AccountDTO transfer(
                @PathVariable Integer fromAccountId,
                @PathVariable Integer toAccountId,
                @RequestBody BigDecimal amount) {

            Account account = accountService.transfer(
                    fromAccountId,
                    toAccountId,
                    amount
            );

            return accountMapper.toDTO(account);
        }


























}
