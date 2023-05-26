package com.app.repository;

import com.app.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Long> {
    @Query("Select od from OrderDetails od join CustomerOrder o on od.order.id = o.id where o.id = :orderId")
    List<OrderDetails> findByOrderId(@Param("orderId") Long orderId);

    @Query("Select od from OrderDetails od " +
            "join CustomerOrder o on od.order.id = o.id " +
            "join Customer c on c.id = o.customer.id where c.id = :customerId")
    List<OrderDetails> findByCustomerId(@Param("customerId") Long customerId);

    @Transactional
    @Modifying
    @Query("Delete from OrderDetails od where od.id = :id")
    int deleteOrderDetailsById(@Param("id") Long id);
}
