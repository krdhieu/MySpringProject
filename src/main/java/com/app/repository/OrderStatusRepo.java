package com.app.repository;

import com.app.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface OrderStatusRepo extends JpaRepository<OrderStatus, Long> {
    @Query("Select os from OrderStatus os where os.name like %:name%")
    List<OrderStatus> findOrderStatusByName(@Param("name") String statusName);

    @Transactional
    @Modifying
    @Query("Delete from OrderStatus os where os.id = :id")
    int deleteOrderStatusById(@Param("id") Long id);
}
