package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferDTO {

    private long userId;
    private BigDecimal amount;

    public TransferDTO(long userId, BigDecimal amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}