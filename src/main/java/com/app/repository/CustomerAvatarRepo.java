package com.app.repository;

import com.app.entity.Customer;
import com.app.entity.CustomerAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CustomerAvatarRepo  extends JpaRepository<CustomerAvatar, Long> {
    @Transactional
    @Modifying
    @Query("Delete from CustomerAvatar avt where avt.id = :avatarId")
    int deleteCustomerAvatarById(@Param("avatarId") long avatarId);
    CustomerAvatar findByCustomer(Customer customer);
}
