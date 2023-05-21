package com.app.repository;

import com.app.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long> {
    @Query("Select od from OrderDetail od join CustomerOrder o on od.order.id = o.id where o.id = :orderId")
    List<OrderDetail> findByOrderId(@Param("orderId") Long orderId);

    @Query("Select od from OrderDetail od " +
            "join CustomerOrder o on od.order.id = o.id " +
            "join Customer c on c.id = o.customer.id where c.id = :customerId")
    List<OrderDetail> findByCustomerId(@Param("customerId") Long customerId);
}
