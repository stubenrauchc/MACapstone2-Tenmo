package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class AppController {

    @Autowired
    AccountDAO accountDao;

    @Autowired
    UserDao userDao;

    @Autowired
    TransferDAO transferDAO;

    @RequestMapping(path="balance", method = RequestMethod.GET)
    public BigDecimal getAccountBalance(Principal principal){
        String username = principal.getName();
        long userId = userDao.findIdByUsername(username);
        BigDecimal balance = accountDao.getBalance(userId);
        return balance;
    }

    @RequestMapping(path="account/{id}", method = RequestMethod.GET)
    public Account getAccountByUserId(@PathVariable long id){return accountDao.getAnAccountByUserId(id);
    }

    @RequestMapping(path="user/{id}", method = RequestMethod.GET)
    public long getUserIdByAccountId(@PathVariable long id){return userDao.findIdByAccountID(id);
    }

    @RequestMapping(path="users", method = RequestMethod.GET)
    public List<User>getAllUsers(Principal principal){
        String username = principal.getName();
        long userID = userDao.findIdByUsername(username);
        return userDao.findAll(userID);
    }

    @RequestMapping(path="transfers", method = RequestMethod.GET)
    public List<Transfer> listTransfers(Principal principal){
        String username = principal.getName();
        long userID = userDao.findIdByUsername(username);
        Account account = accountDao.getAnAccountByUserId(userID);
        long accountId = account.getAccountId();
        List<Transfer> transferList = transferDAO.getAllApprovedTransfers(accountId);
        return  transferList;

    }

    @RequestMapping(path="transfers/pending", method = RequestMethod.GET)
    public List<Transfer> listPendingTransfers(Principal principal){
        String username = principal.getName();
        long userID = userDao.findIdByUsername(username);
        Account account = accountDao.getAnAccountByUserId(userID);
        long accountId = account.getAccountId();
        List<Transfer> transferList = transferDAO.getAllPendingTransfers(accountId);
        return  transferList;
    }

    @RequestMapping(path="transfers/{transferId}", method = RequestMethod.GET)
    public Transfer transferDetails (@PathVariable long transferId){
        return transferDAO.getTransferById(transferId);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="transfers", method = RequestMethod.POST)
    public Transfer startTransfer (Principal principal, @Valid @RequestBody TransferDTO transferDTO) {
        String username = principal.getName();
        long userID = userDao.findIdByUsername(username);
        Transfer transfer = transferDAO.newTransfer(userID, transferDTO.getUserId(),transferDTO.getAmount());

        return transfer;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="requests", method = RequestMethod.POST)
    public Transfer requestTransfer(Principal principal, @Valid @RequestBody TransferDTO transferDTO){
        String username = principal.getName();
        long userId = userDao.findIdByUsername(username);
        Transfer transfer = transferDAO.newRequest(transferDTO.getUserId(), userId, transferDTO.getAmount());

        return transfer;
    }

    @RequestMapping(path="transfer/{transferId}/accept", method = RequestMethod.PUT)
    public boolean acceptTransfer(Principal principal, @Valid @RequestBody TransferDTO transferDTO, @PathVariable long transferId) {
        String usernameFrom = principal.getName();
        long userFromId = userDao.findIdByUsername(usernameFrom);

        return transferDAO.acceptRequest(userFromId, transferDTO.getUserId(), transferDTO.getAmount(), transferId);
    }

    @RequestMapping(path="transfer/{transferId}/reject", method = RequestMethod.PUT)
    public boolean rejectTransfer(Principal principal, @Valid @RequestBody TransferDTO transferDTO, @PathVariable long transferId) {
        String usernameFrom = principal.getName();
        long userFromId = userDao.findIdByUsername(usernameFrom);

        return transferDAO.rejectRequest(transferId);
    }


    @RequestMapping(path="username/{accountId}", method = RequestMethod.GET)
    public String username (@PathVariable long accountId){
        return userDao.findUserByAccountID(accountId);
    }




}