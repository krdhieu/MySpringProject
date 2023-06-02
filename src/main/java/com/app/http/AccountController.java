package com.app.http;

import com.app.config.security.MyUserDetails;
import com.app.entity.Account;
import com.app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    UserDetailsService userDetailsService;

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<Account>> getAllAccount() {
        return new ResponseEntity<>(accountService.getAllAccount(), HttpStatus.OK);
    }

    @GetMapping("/find-by-username/{username}")
    public @ResponseBody ResponseEntity<Account> findAccountByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<>(accountService.findAccountByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public @ResponseBody ResponseEntity<Account> findAccountById(@PathVariable("id") Long id, Authentication authentication) {
        MyUserDetails myUserDetails = (MyUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
        if (myUserDetails.isAdmin() || myUserDetails.isCurrentAccount(id))
            return new ResponseEntity<>(accountService.findAccountById(id), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }

    @PostMapping("/create-account")
    public @ResponseBody ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.CREATED);
    }

    @PutMapping("/update-account")
    public @ResponseBody ResponseEntity<Account> updateAccount(@RequestBody Account account, Authentication authentication) {
        MyUserDetails myUserDetails = (MyUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
        if (myUserDetails.isAdmin() || myUserDetails.isCurrentAccount(account.getId()))
            return new ResponseEntity<>(accountService.updateAccount(account), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public @ResponseBody ResponseEntity<Integer> deleteAccountById(@PathVariable("id") Long id, Authentication authentication) {
            return new ResponseEntity<>(accountService.deleteAccountById(id), HttpStatus.OK);
    }
}
