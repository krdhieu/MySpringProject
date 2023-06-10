package com.app.logic;

import com.app.entity.Product;
import com.app.entity.ProductImage;
import com.app.repository.ProductImageRepo;
import com.app.upload.FileUploadLogic;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Component
public class ProductImageLogic {
    @Autowired
    ProductImageRepo productImageRepo;
    @Autowired
    FileUploadLogic fileUploadLogic;

    @Autowired
    @Qualifier("appUrl")
    String appUrl;

    public ProductImage findProductImageById(Long id) {
        if (id == null) return null;
        Optional<ProductImage> productImageOptional = productImageRepo.findById(id);
        if (productImageOptional.isPresent())
            return productImageOptional.get();
        return null;
    }


    public ProductImage findByProductImageByProduct(Product product) {
        if (product == null) return null;
        return productImageRepo.findByProduct(product);
    }

    public ProductImage addProductImage(String filePath, Product product) {
        ProductImage newProductImg = new ProductImage();
        newProductImg.setImgPath(appUrl + filePath);
        newProductImg.setProduct(product);
        return productImageRepo.save(newProductImg);
    }

    public ProductImage updateProductImage(String filePath, Product product) {
        ProductImage currentProductImg = productImageRepo.findByProduct(product);
        if (currentProductImg != null) {
            currentProductImg.setImgPath(appUrl + filePath);
            return productImageRepo.save(currentProductImg);
        }
        return null;
    }

    public int deleteProductImage(Long id) throws IOException {
        ProductImage currentImg = this.findProductImageById(id);
        if (currentImg == null) return 0;
        int rowEffected = productImageRepo.deleteProductImageById(id);
        if (rowEffected == 1) {
            String filePath = "src/main/resources/" + fileUploadLogic.extractPathFromImgPath(currentImg.getImgPath());
            Path path = Paths.get(filePath);
            Files.delete(path);
        }
        return rowEffected;
    }
}
