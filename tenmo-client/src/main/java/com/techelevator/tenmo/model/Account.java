package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {
    // Properties
    private long userId;
    private long accountId;
    private BigDecimal balance;





    // Getters and setters
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}