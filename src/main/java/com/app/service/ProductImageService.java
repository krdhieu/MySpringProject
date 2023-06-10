package com.app.service;

import com.app.entity.Product;
import com.app.entity.ProductImage;
import com.app.logic.ProductImageLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProductImageService {
    @Autowired
    ProductImageLogic productImageLogic;

    public ProductImage findProductImageById(Long id) {
        return productImageLogic.findProductImageById(id);
    }

    public ProductImage findByProductImageByProduct(Product product) {
        return productImageLogic.findByProductImageByProduct(product);
    }

    public ProductImage addProductImage(String filePath, Product product) {
        return productImageLogic.addProductImage(filePath, product);
    }

    public ProductImage updateProductImage(String filePath, Product product) {
        return productImageLogic.updateProductImage(filePath, product);
    }

    public int deleteProductImage(Long id) throws IOException {
        return productImageLogic.deleteProductImage(id);
    }

}
