package com.app.repository;

import com.app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    @Query("Select c from Customer c where c.name like %:name%")
    List<Customer> findByName(@Param("name") String name);

    @Modifying
    @Query("Delete from Customer c where c.id = :customerId")
    int deleteCustomerById(@Param("customerId") Long id);

//    @Query("Select c from Customer c inner join Account a where c.id = a.")
//    Customer findCustomerByUsernameNPassword(@Param("username") String username,@Param("password") String password);
}
