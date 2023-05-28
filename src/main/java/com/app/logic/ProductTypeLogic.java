package com.app.logic;

import com.app.entity.ProductType;
import com.app.logic.common.EntityLogic;
import com.app.repository.ProductTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Component
public class ProductTypeLogic implements EntityLogic<ProductType, Long> {
    @Autowired
    ProductTypeRepo productTypeRepo;

    public List<ProductType> getAllProductType() {
        return productTypeRepo.findAll();
    }

    public List<ProductType> findProductTypeByName(String name) {
        return productTypeRepo.findProductTypeByName(name);
    }

    public ProductType findProductTypeById(Long id) {
        Optional<ProductType> productTypeOptional = productTypeRepo.findById(id);
        if (productTypeOptional.isPresent())
            return productTypeOptional.get();
        return null;
    }

    public ProductType saveProductType(ProductType productType) {
        ProductType pt = this.findProductTypeById(productType.getId());
        if (pt != null) {
            pt.withName(productType.getName());
            return productTypeRepo.save(pt);
        }
        return null;
    }

    public ProductType createProductType(ProductType productType) {
        return productTypeRepo.save(productType);
    }

    public int deleteProductType(Long id) {
        return productTypeRepo.deleteProductTypeById(id);
    }

    @Override
    public void saveEntity(ProductType entity) {
        productTypeRepo.save(entity);
    }

    @Override
    @Transactional
    public ProductType findById(Long id) {
        Optional<ProductType> productTypeOptional = productTypeRepo.findById(id);
        if (productTypeOptional.isPresent())
            return productTypeOptional.get();
        return null;
    }


}
