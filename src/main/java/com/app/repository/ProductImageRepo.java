package com.app.repository;

import com.app.entity.Product;
import com.app.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProductImageRepo extends JpaRepository<ProductImage, Long> {
    @Transactional
    @Modifying
    @Query("Delete from ProductImage img where img.id = :imgId")
    int deleteProductImageById(@Param("imgId") long imgId);
    ProductImage findByProduct(Product product);
}
