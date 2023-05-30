package com.app.repository;

import com.app.entity.CustomerOrder;
import com.app.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface CustomerOrderRepo extends JpaRepository<CustomerOrder, Long> {
    @Query("Select co from CustomerOrder co join co.customer cu where cu.id = :customerId AND (:orderedDate IS NULL OR co.orderAt = :orderedDate)")
    List<CustomerOrder> findCustomerOrderByCustomerIdAndOrDate(@Param("customerId") Long customerId, @Param("orderedDate") Date orderedDate);

    @Query("Select co from CustomerOrder co where co.createAt = :date")
    List<CustomerOrder> findCustomerOrderByCreatedDate(@Param("date") Date dateCreated);

    @Query("Select co from CustomerOrder co join co.status s where s.id = statusId")
    List<CustomerOrder> findCustomerOrderByOrderStatus(@Param("statusId") Long orderStatus);

    @Transactional
    @Modifying
    @Query("Delete from CustomerOrder co where co.id = :id")
    int deleteCustomerOrderById(@Param("id") long customerOrderId);
}
