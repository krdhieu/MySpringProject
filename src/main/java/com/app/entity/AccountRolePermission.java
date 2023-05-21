package com.app.entity;

import com.app.entity.generics.BaseEntity;

import javax.persistence.*;

@Entity
public class AccountRolePermission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public AccountRolePermission withAccount(Account account) {
        this.account = account;
        return this;
    }

    public AccountRolePermission withRole(Role role) {
        this.role = role;
        return this;
    }

    public AccountRolePermission withPermission(Permission permission) {
        this.permission = permission;
        return this;
    }

    @Override
    public String toString() {
        return "AccountRolePermission{" +
                "id=" + id +
                ", role=" + role +
                ", permission=" + permission +
                ", account=" + account +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
