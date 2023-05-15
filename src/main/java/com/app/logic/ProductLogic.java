package com.app.logic;

import com.app.entity.Product;
import com.app.entity.ProductType;
import com.app.repository.ProductRepo;
import com.app.repository.ProductTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ProductLogic implements EntityLogic<Product, Long> {
    @Autowired
    ProductRepo productRepo;

    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    public Product findProductById(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isPresent())
            return productOptional.get();
        return null;
    }

    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    public Product updateProduct(Product product) {
        Product existedProduct = this.findProductById(product.getId());
        if (existedProduct != null) {
            existedProduct
                    .withName(product.getName())
                    .withProductType(product.getType())
                    .withPrice(product.getPrice())
                    .withDescription(product.getDescription())
                    .withUpdateAt(new Date());
            productRepo.save(existedProduct);
            return existedProduct;
        }
        return null;
    }

    public List<Product> findProductByName(String name) {
        return productRepo.findProductByName(name);
    }

    public int deleteProductById(Long id) {
        return productRepo.deleteProductById(id);
    }

    public List<Product> findProductByProductType(Long  productTypeId) {
        return productRepo.findProductByProductType(productTypeId);
    }

    @Override
    public void saveEntity(Product entity) {
        productRepo.save(entity);
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isPresent())
            return productOptional.get();
        return null;
    }
}
