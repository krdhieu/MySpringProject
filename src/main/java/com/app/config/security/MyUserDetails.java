package com.app.config.security;

import com.app.auth.AuthRequest;
import com.app.entity.Account;
import com.app.entity.AccountRolePermission;
import com.app.logic.AccountRolePermissionLogic;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class MyUserDetails implements UserDetails {
    Account account;
    AccountRolePermissionLogic accountRolePermissionLogic;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<AccountRolePermission> accountRolePermissions = accountRolePermissionLogic.findRolePermissionByAccountId(account.getId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (accountRolePermissions.isEmpty()) return authorities;
        authorities.add(new SimpleGrantedAuthority(accountRolePermissions.get(0).getRole().getName()));
        accountRolePermissions.stream().map(accountRolePermission -> {
            String permissionName = accountRolePermission.getPermission().getName();
            return new SimpleGrantedAuthority(permissionName);
        }).forEach(authorities::add);
        return authorities;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public MyUserDetails setAccount(Account account) {
        this.account = account;
        return this;
    }

    public MyUserDetails setAccountRolePermissionLogic(AccountRolePermissionLogic accountRolePermissionLogic) {
        this.accountRolePermissionLogic = accountRolePermissionLogic;
        return this;
    }
}
