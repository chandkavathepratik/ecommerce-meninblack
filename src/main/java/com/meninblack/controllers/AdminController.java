package com.meninblack.controllers;

import com.meninblack.entities.User;
import com.meninblack.entities.sub.Order;
import com.meninblack.service.AdminService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/user-details")
    public ResponseEntity<List<User>> getUserDetails(){
        try {
            return new ResponseEntity<>(adminService.getAllUserDetails(), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all-orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        try {
            return new ResponseEntity<>(adminService.getAllOrders(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/placed-orders")
    public ResponseEntity<List<Order>> getAllPlacedOrders(){
        try {
            return new ResponseEntity<>(adminService.getAllPlacedOrders(), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pending-orders")
    public ResponseEntity<List<Order>> getAllPendingOrders(){
        try {
            return new ResponseEntity<>(adminService.getAllPendingOrders(), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/orders-history")
    public ResponseEntity<List<Order>> getOrdersHistory(){
        try {
            return new ResponseEntity<>(adminService.getOrdersHistory(), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId) {
        try {
            return new ResponseEntity<>(adminService.getOrderById(orderId), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}