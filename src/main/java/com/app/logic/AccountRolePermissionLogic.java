package com.app.logic;

import com.app.entity.AccountRolePermission;
import com.app.logic.generic.EntityLogic;
import com.app.repository.AccountRolePermissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AccountRolePermissionLogic implements EntityLogic<AccountRolePermission, Long> {
    @Autowired
    AccountRolePermissionRepo accountRolePermissionRepo;

    public List<AccountRolePermission> getAllAccountRolePermission() {
        return accountRolePermissionRepo.findAll();
    }
    public AccountRolePermission findAccountRolePermissionById(Long id) {
        Optional<AccountRolePermission> accountRolePermissionOptional = accountRolePermissionRepo.findById(id);
        if (accountRolePermissionOptional.isPresent())
            return accountRolePermissionOptional.get();
        return null;
    }

    public List<AccountRolePermission> findRolePermissionByAccountId(Long accountId) {
        return accountRolePermissionRepo.findRolePermissionByAccountId(accountId);
    }

    public AccountRolePermission createAccountRolePermission(AccountRolePermission accountRolePermission) {
        return accountRolePermissionRepo.save(accountRolePermission);
    }

    public int deleteAccountRolePermissionById(Long accountRolePermissionId) {
        return accountRolePermissionRepo.deleteAccountRolePermissionById(accountRolePermissionId);
    }

    @Override
    public void saveEntity(AccountRolePermission entity) {
        accountRolePermissionRepo.save(entity);
    }

    @Override
    public AccountRolePermission findById(Long id) {
        Optional<AccountRolePermission> accountRolePermissionOptional = accountRolePermissionRepo.findById(id);
        if (accountRolePermissionOptional.isPresent())
            return accountRolePermissionOptional.get();
        return null;
    }
}
