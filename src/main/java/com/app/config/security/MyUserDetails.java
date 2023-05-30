package com.app.config.security;

import com.app.auth.AuthRequest;
import com.app.entity.Account;
import com.app.entity.AccountRolePermission;
import com.app.entity.Customer;
import com.app.logic.AccountRolePermissionLogic;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    Account account;
    Customer customer;
    AccountRolePermissionLogic accountRolePermissionLogic;

    public MyUserDetails setAccount(Account account) {
        this.account = account;
        return this;
    }

    public Account getAccount() {
        return this.account;
    }

    public MyUserDetails setAccountRolePermissionLogic(AccountRolePermissionLogic accountRolePermissionLogic) {
        this.accountRolePermissionLogic = accountRolePermissionLogic;
        return this;
    }

    public MyUserDetails setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public boolean isAdmin() {
        if (this.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")))
            return true;
        return false;
    }

    public boolean isCurrentUser(long customerId) {
        if (this.getAccount() != null) {
            if (this.getCustomer().getId() == customerId)
                return true;
            return false;
        }
        return false;
    }

    public boolean isCurrentAccount(long accountId) {
        if (this.getAccount() != null) {
            if (this.getAccount().getId() == accountId) {
                return true;
            }
            return false;
        }
        return false;
    }

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
        if (this.account != null)
            return account.getPassword();
        return null;
    }

    @Override
    public String getUsername() {
        if (this.account != null)
            return account.getUsername();
        return null;
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
}
