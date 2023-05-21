package com.app.logic;

import com.app.entity.Account;
import com.app.logic.generic.EntityLogic;
import com.app.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AccountLogic implements EntityLogic<Account, Long> {
    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public List<Account> getAllAccount() {
        return accountRepo.findAll();
    }

    public Account findAccountById(Long id) {
        Optional<Account> accountOptional = accountRepo.findById(id);
        if (accountOptional.isPresent())
            return accountOptional.get();
        return null;
    }

    public Account findAccountByUsername(String username) {
        return accountRepo.findByUsername(username);
    }

    public Account createAccount(Account account) {
        account.withPassword(this.encodePassword(account.getPassword()));
        return accountRepo.save(account);
    }

    public int deleteAccountById(Long id) {
        return accountRepo.deleteAccountById(id);
    }

    public Account updateAccount(Account account) {
        Account existedAccount = accountRepo.findByUsername(account.getUsername());
        if(existedAccount != null)
            return existedAccount.withPassword(account.getPassword());
        return null;
    }

    @Override
    public void saveEntity(Account entity) {
        entity.withPassword(this.encodePassword(entity.getPassword()));
        accountRepo.save(entity);
    }

    @Override
    public Account findById(Long id) {
        Optional<Account> accountOptional = accountRepo.findById(id);
        if (accountOptional.isPresent())
            return accountOptional.get();
        return null;
    }
}
