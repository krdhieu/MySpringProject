package com.app.service;

import com.app.entity.ProductType;
import com.app.logic.ProductTypeLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService {
    @Autowired
    private ProductTypeLogic productTypeLogic;

    public List<ProductType> getAllProductType() {
        return productTypeLogic.getAllProductType();
    }

    public List<ProductType> findProductTypeByName(String name) {

        return productTypeLogic.findProductTypeByName(name);
    }

    public ProductType findProductTypeById(Long id) {
        return productTypeLogic.findProductTypeById(id);
    }

    public ProductType createProductType(ProductType productType) {
        return productTypeLogic.createProductType(productType);
    }

    public ProductType saveProductType(ProductType productType) {
        return productTypeLogic.saveProductType(productType);
    }

    public int deleteProductTypeById(Long id) {
        return productTypeLogic.deleteProductType(id);
    }
}
