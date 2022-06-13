package com.techelevator.tenmo.dao;
import com.techelevator.tenmo.model.Account;
import java.math.BigDecimal;

public interface AccountDAO {

    BigDecimal getBalance(long id);

    Account getAnAccountByUserId(long userId);

    void addBalance(BigDecimal amount, long accountId);

    boolean subtractBalance(BigDecimal amount, long accountId);
}