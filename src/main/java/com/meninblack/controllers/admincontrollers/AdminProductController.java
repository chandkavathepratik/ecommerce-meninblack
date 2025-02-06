package com.meninblack.controllers.admincontrollers;

import com.meninblack.entities.Product;
import com.meninblack.service.adminservice.AdminProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminProductController {

    @Autowired
    private AdminProductService adminProductService;

    @PostMapping("/add-new-product")
    public ResponseEntity<String> addNewProduct(@RequestBody Product product){
        try {
            adminProductService.addNewProduct(product);
            return new ResponseEntity<>("Product added to successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-product/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable String productId, @RequestBody Product product){
        try {
            adminProductService.updateProduct(productId, product);
            return new ResponseEntity<>("Product updated successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-product/{prodId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String prodId){
        try {
            adminProductService.deleteProduct(prodId);
            return new ResponseEntity<>("Product deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/mark-as-out-of-stock/{prodId}")
    public ResponseEntity<String> markAsOutOfStock(@PathVariable String prodId) {
        try {
            adminProductService.markAsOutOfStock(prodId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/mark-as-in-stock/{prodId}")
    public ResponseEntity<String> markAsInStock(@PathVariable String prodId) {
        try {
            adminProductService.markAsInStock(prodId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
