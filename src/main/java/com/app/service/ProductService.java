package com.app.service;

import com.app.entity.Product;
import com.app.entity.ProductType;
import com.app.logic.ProductLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    @Autowired
    ProductLogic productLogic;

    public List<Product> getAllProduct() {
        return productLogic.getAllProduct();
    }

    public Product findProductById(Long id) {
        return productLogic.findProductById(id);
    }

    public List<Product> findProductByName(String name) {
        return productLogic.findProductByName(name);
    }

    public List<Product> findProductByProductType(Long productTypeId) {
        return productLogic.findProductByProductType(productTypeId);
    }

    public Product createProduct(Product product) {
        return productLogic.createProduct(product);
    }

    public Product updateProduct(Product product) {
        return productLogic.updateProduct(product);
    }

    public int deleteProduct(Long id) {
        return productLogic.deleteProductById(id);
    }
}
