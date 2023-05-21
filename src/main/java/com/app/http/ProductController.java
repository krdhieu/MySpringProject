package com.app.http;

import com.app.entity.Product;
import com.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<Product>> getAllProduct() {
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public @ResponseBody ResponseEntity<Product> findProductById(@PathVariable("id") Long id) {
        return new ResponseEntity<> (productService.findProductById(id), HttpStatus.OK);
    }

    @GetMapping("/find-by-name/{name}")
    public @ResponseBody ResponseEntity<List<Product>> findProductByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(productService.findProductByName(name), HttpStatus.OK);
    }

    @GetMapping("/find-by-product-type/{typeId}")
    public @ResponseBody ResponseEntity<List<Product>> findProductByProductType(@PathVariable("typeId") Long typeId) {
        return new ResponseEntity<>(productService.findProductByProductType(typeId), HttpStatus.OK);
    }

    @PostMapping("/create-product")
    public @ResponseBody ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.OK);
    }

    @PutMapping("/update-product")
    public @ResponseBody ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public @ResponseBody ResponseEntity<Integer> deleteProductById(@PathVariable("productId") Long id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.ACCEPTED);
    }
}
