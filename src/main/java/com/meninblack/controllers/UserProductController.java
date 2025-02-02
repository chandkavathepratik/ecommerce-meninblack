package com.meninblack.controllers;

import com.meninblack.entities.enums.product.Category;
import com.meninblack.entities.sub.CartItem;
import com.meninblack.entities.Product;
import com.meninblack.entities.sub.Order;
import com.meninblack.service.UserProductService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class UserProductController {

    @Autowired
    private UserProductService userProductService;



    @PostMapping("/add-to-cart")
    public ResponseEntity<String> addToCart(@RequestBody CartItem item){
        try {
            return new ResponseEntity<>(userProductService.addToCart(item), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-to-wishlist/{prodId}")
    public ResponseEntity<String> addToWishlist(@PathVariable String prodId){
        try {
            userProductService.addToWishlist(prodId);
            return new ResponseEntity<>("Product added to wishlist.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/get-cart")
    public ResponseEntity<List<CartItem>> getCart(){
        try {
            return new ResponseEntity<>(userProductService.getCart(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/get-wishlist")
    public ResponseEntity<List<Product>> getWishlist(){
        try {;
            return new ResponseEntity<>(userProductService.getWishlist(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
