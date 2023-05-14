package com.app.repository;

import com.app.entity.Product;
import com.app.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Modifying
    @Transactional
    @Query("Delete from Product p where p.id = :id")
    int deleteProductById(@Param("id") Long id);

    @Query("Select pd from Product pd where pd.name like %:name%")
    List<Product> findProductByName(@Param("name") String productName);

    @Query("Select pd from Product pd inner join pd.productType pt where pd.productType.id = :productTypeId")
    List<Product> findProductByProductType(@Param("productTypeId") Long productTypeId);
}
