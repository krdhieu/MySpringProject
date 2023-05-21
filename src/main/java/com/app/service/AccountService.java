package com.app.service;

import com.app.entity.Account;
import com.app.logic.AccountLogic;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountLogic accountLogic;

    public List<Account> getAllAccount() {
        return accountLogic.getAllAccount();
    }

    public Account findAccountById(Long id) {
        return accountLogic.findAccountById(id);
    }

    public Account findAccountByUsername(String username) {
        return accountLogic.findAccountByUsername(username);
    }

    public Account createAccount(Account account) {
        return accountLogic.createAccount(account);
    }

    public int deleteAccountById(Long id) {
        return accountLogic.deleteAccountById(id);
    }

    public Account updateAccount(Account account) {
        return accountLogic.updateAccount(account);
    }
}
