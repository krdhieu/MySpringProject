package com.app.repository;

import com.app.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface ProductTypeRepo extends JpaRepository<ProductType, Long> {
    @Query("Select pt from ProductType pt where pt.name like %:name%")
    List<ProductType> findProductTypeByName(@Param("name") String name);
    @Modifying
    @Transactional
    @Query("Delete ProductType pt where pt.id = :id")
    int deleteProductTypeById(@Param("id") Long productTypeId);
}
