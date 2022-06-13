package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    // properties
    private long transferId;
    private long transferTypeId;
    private String transferTypeDesc;
    private long transferStatusId;
    private String transferStatusDesc;
    private long accountFrom;
    private long accountTo;
    private BigDecimal amount;



    // getters and setters
    public long getTransferId() {
        return transferId;
    }

    public void setTransferId(long transferId) {
        this.transferId = transferId;
    }

    public long getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(long transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public long getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(long transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public long getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(long accountFrom) {
        this.accountFrom = accountFrom;
    }

    public long getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(long accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransferTypeDesc() {
        return transferTypeDesc;
    }

    public void setTransferTypeDesc(String transferTypeDesc) {
        this.transferTypeDesc = transferTypeDesc;
    }

    public String getTransferStatusDesc() {
        return transferStatusDesc;
    }

    public void setTransferStatusDesc(String transferStatusDesc) {
        this.transferStatusDesc = transferStatusDesc;
    }


    @Override
    public String toString() {
        return "\nId: " + transferId +
                "\nType: " + transferTypeDesc  +
                "\nStatus: " + transferStatusDesc +
                "\nFrom: " + accountFrom +
                "\nTo: " + accountTo +
                "\nAmount: $" + amount;
    }
}