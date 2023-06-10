package com.app.http;

import com.app.config.security.MyUserDetails;
import com.app.entity.Product;
import com.app.entity.ProductImage;
import com.app.repository.ProductImageRepo;
import com.app.service.ProductImageService;
import com.app.service.ProductService;
import com.app.upload.FileUploadService;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Past;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/product-image")
public class ProductImageController {
    @Autowired
    ProductImageService productImageService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    ProductService productService;

    @Autowired
    @Qualifier("productDir")
    String productDir;

    @GetMapping("/find-by-id/{id}")
    public @ResponseBody ResponseEntity<ProductImage> findProductImageById(@PathVariable("id") Long imgId) {
        if (imgId == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(productImageService.findProductImageById(imgId), HttpStatus.OK);
    }

    @GetMapping("/find-by-product/{productId}")
    public @ResponseBody ResponseEntity<ProductImage> findProductImageByProduct(@PathVariable("productId") Long productId) {
        if (productId == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Product product = productService.findProductById(productId);
        if (product == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productImageService.findByProductImageByProduct(product), HttpStatus.OK);
    }

    @PostMapping("/upload-product-image/{productId}")
    public @ResponseBody ResponseEntity<ProductImage> uploadProductImage(
            @PathVariable("productId") Long productId,
            @RequestParam("imageFile") MultipartFile file,
            Authentication authentication
    ) {
        if (productId == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        String fileType = file.getContentType();
        long fileSize = file.getSize();
        if (!fileType.equals("image/png") && !fileType.equals("image/jpeg") && !fileType.equals("image/jpg") && fileSize <= 5 * 1024 * 1024) {
            return new ResponseEntity("Invalid file type. Only PNG, JPEG, and JPG files are allowed, file size have to <= 5MB", HttpStatus.BAD_REQUEST);
        }
        Product product = productService.findProductById(productId);
        if (product == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (file == null || file.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        String uploadDir = productDir;
        String filePath = fileUploadService.uploadFile(file, uploadDir);
        if (productImageService.findByProductImageByProduct(product) == null)
            return new ResponseEntity<>(productImageService.addProductImage(filePath, product), HttpStatus.OK);
        return new ResponseEntity<>(productImageService.updateProductImage(filePath, product), HttpStatus.OK);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<Integer> deleteProductImageById(@PathVariable("id") Long productImgId) {
        if (productImgId == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        ProductImage currentProductImg = productImageService.findProductImageById(productImgId);
        if (currentProductImg == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            return new ResponseEntity<>(productImageService.deleteProductImage(productImgId), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
