package com.app.http;

import com.app.entity.ProductType;
import com.app.logic.ProductTypeLogic;
import com.app.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-type")
public class ProductTypeController {
    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<ProductType>> getAllProductType() {
        return new ResponseEntity<>(productTypeService.getAllProductType(), HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public @ResponseBody ResponseEntity<ProductType> findProductTypeById(@PathVariable Long id) {
        return new ResponseEntity<>(productTypeService.findProductTypeById(id), HttpStatus.OK);
    }

    @GetMapping("/find-by-name/{name}")
    public @ResponseBody ResponseEntity<List<ProductType>> findProductTypeByName(@PathVariable String name) {
        return new ResponseEntity<>(productTypeService.findProductTypeByName(name), HttpStatus.OK);
    }

    @PostMapping("/create-product-type")
    public @ResponseBody ResponseEntity<ProductType> createProductType(@RequestBody ProductType productType) {
        return new ResponseEntity<>(productTypeService.createProductType(productType), HttpStatus.OK);
    }

    @PutMapping("/update-product-type")
    public @ResponseBody ResponseEntity<ProductType> updateProductType(@RequestBody ProductType productType) {
        return new ResponseEntity<>(productTypeService.saveProductType(productType), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public @ResponseBody ResponseEntity<Integer> deleteProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productTypeService.deleteProductTypeById(id), HttpStatus.ACCEPTED);
    }
}
