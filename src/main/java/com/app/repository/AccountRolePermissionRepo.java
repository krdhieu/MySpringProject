package com.app.repository;

import com.app.entity.AccountRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AccountRolePermissionRepo extends JpaRepository<AccountRolePermission, Long> {
    @Transactional
    @Query("Select arp from AccountRolePermission arp " +
            "join arp.account a " +
            "join arp.role r " +
            "join arp.permission p " +
            "where a.id = :accountId ")
    List<AccountRolePermission> findRolePermissionByAccountId(@Param("accountId") Long accountId);

    @Transactional
    @Modifying
    @Query("Delete from AccountRolePermission arp where arp.id = :id")
    int deleteAccountRolePermissionById(@Param("id") Long id);
}
