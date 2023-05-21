package com.app.repository;

import com.app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
    @Query("Delete from Account a where a.id = :id")
    int deleteAccountById(@Param("id") Long id);
}
