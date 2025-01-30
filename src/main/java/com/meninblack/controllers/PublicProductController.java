package com.meninblack.controllers;

import com.meninblack.entities.Product;
import com.meninblack.entities.enums.product.Availability;
import com.meninblack.entities.enums.product.Category;
import com.meninblack.service.PublicProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/")
public class PublicProductController {

    @Autowired
    private PublicProductService publicProductService;

    @GetMapping("/all-products")
    public ResponseEntity<List<Product>> getAllProduct(){
        try {
            List<Product> prod = publicProductService.getAllProducts();
            return new ResponseEntity<>(prod, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(List.of(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-product/{prodId}")
    public ResponseEntity<Product> getProduct(@PathVariable String prodId){
        try {
            Product prod = publicProductService.getProduct(prodId);
            return new ResponseEntity<>(prod, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<Product>> findByCategory(@RequestParam (value = "category") Category category) {
        try {
            return new ResponseEntity<>(publicProductService.findByCategory(category), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{availability}")
    public ResponseEntity<List<Product>> findByAvailability(@RequestParam (value = "availability") Availability availability) {
        try {
            return new ResponseEntity<>(publicProductService.findByAvailability(availability), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
