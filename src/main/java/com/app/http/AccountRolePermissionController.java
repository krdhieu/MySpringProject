package com.app.http;

import com.app.entity.Account;
import com.app.entity.AccountRolePermission;
import com.app.logic.AccountRolePermissionLogic;
import com.app.service.AccountRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account-role-permission")
public class AccountRolePermissionController {
    @Autowired
    AccountRolePermissionService accountRolePermissionService;

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<AccountRolePermission>> getAllAccountRollPermission() {
        return new ResponseEntity<>(accountRolePermissionService.getAllAccountRolePermission(), HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public @ResponseBody ResponseEntity<AccountRolePermission> findAccountRolePermissionById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(accountRolePermissionService.findRolePermissionById(id), HttpStatus.OK);
    }

    @GetMapping("/find-by-account-id/{accountId}")
    public @ResponseBody ResponseEntity<List<AccountRolePermission>> findRolePermissionByAccountId(@PathVariable("accountId") Long accountId) {
        return new ResponseEntity<>(accountRolePermissionService.findRolePermissionByAccountId(accountId), HttpStatus.OK);
    }

    @GetMapping("/find-role-permission-name-by-account-id/{accountId}")
    public @ResponseBody ResponseEntity<List<Object[]>> findRolePermissionNameByAccountId(@PathVariable("accountId") Long accountId) {
        return new ResponseEntity<>(accountRolePermissionService.findRolePermissionNameByAccountId(accountId), HttpStatus.OK);
    }

    @PostMapping("/create-account-role-permission")
    public @ResponseBody ResponseEntity<AccountRolePermission> createAccountRolePermission(@RequestBody AccountRolePermission accountRolePermission) {
        return new ResponseEntity<>(accountRolePermissionService.createAccountRolePermission(accountRolePermission), HttpStatus.OK);
    }

    @DeleteMapping("/delete-account-role-permission-by-id/{id}")
    public @ResponseBody ResponseEntity<Integer> deleteAccountRolePermissionById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(accountRolePermissionService.deleteAccountRolePermissionById(id), HttpStatus.ACCEPTED);
    }
}
