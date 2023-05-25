package com.app.config.security;

import com.app.entity.Account;
import com.app.entity.AccountRolePermission;
import com.app.logic.AccountRolePermissionLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class MyUserDetail implements UserDetails {
    Account account;

    @Autowired
    AccountRolePermissionLogic accountRolePermissionLogic;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<AccountRolePermission> accountRolePermissions = accountRolePermissionLogic.findRolePermissionByAccountId(account.getId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(accountRolePermissions.isEmpty()) return authorities;
        authorities.add(new SimpleGrantedAuthority(accountRolePermissions.get(0).getRole().getName()));
        accountRolePermissions.stream().map(accountRolePermission -> {
            String permissionName = accountRolePermission.getPermission().getName();
            return new SimpleGrantedAuthority(permissionName);
        }).forEach(authorities::add);
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
