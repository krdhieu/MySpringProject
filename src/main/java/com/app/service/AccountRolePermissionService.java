package com.app.service;

import com.app.entity.AccountRolePermission;
import com.app.logic.AccountRolePermissionLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRolePermissionService {
    @Autowired
    AccountRolePermissionLogic accountRolePermissionLogic;

    public List<AccountRolePermission> getAllAccountRolePermission() {
        return accountRolePermissionLogic.getAllAccountRolePermission();
    }

    public AccountRolePermission findRolePermissionById(Long id) {
        return accountRolePermissionLogic.findAccountRolePermissionById(id);
    }

    public List<AccountRolePermission> findRolePermissionByAccountId(Long accountId) {
        return accountRolePermissionLogic.findRolePermissionByAccountId(accountId);
    }

    public AccountRolePermission createAccountRolePermission(AccountRolePermission accountRolePermission) {
        return accountRolePermissionLogic.createAccountRolePermission(accountRolePermission);
    }

    public int deleteAccountRolePermissionById(Long id) {
        return accountRolePermissionLogic.deleteAccountRolePermissionById(id);
    }

}
