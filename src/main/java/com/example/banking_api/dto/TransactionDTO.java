package com.example.banking_api.dto;

import com.example.banking_api.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {
    private Integer id;
    private BigDecimal amount;
    private LocalDateTime date;
    private TransactionType type;
    private Integer accountId;

    public TransactionDTO() {
    }

    public TransactionDTO(Integer id, BigDecimal amount, LocalDateTime date,
                          TransactionType type, Integer accountId) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.accountId = accountId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
