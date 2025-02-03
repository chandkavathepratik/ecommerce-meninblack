package com.meninblack.controllers;

import com.meninblack.entities.enums.payment.PaymentStatus;
import com.meninblack.entities.sub.Order;
import com.meninblack.service.UserOrderService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class UserOrderController {

    @Autowired
    private UserOrderService userOrderService;

    @PostMapping("/place-order/{addId}")
    public ResponseEntity<com.razorpay.Order> placeOrder(@PathVariable String addId) {
        try {
            return new ResponseEntity<>(userOrderService.placeOrder(addId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-order-status")
    public ResponseEntity<String> updateOrderStatus(@RequestParam String paymentId, @RequestParam PaymentStatus status, @RequestParam String paymentMethod) {
        try {
            return new ResponseEntity<>(userOrderService.updateOrderStatus(paymentId, status, paymentMethod), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
        try {
            Order order = userOrderService.getOrderById(orderId);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        try {
            return ResponseEntity.ok(userOrderService.getAllOrders());
        } catch (Exception e) {
            return new ResponseEntity<>(List.of(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @DeleteMapping("/{OrderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable String OrderId) {
        try {
            return new ResponseEntity<>(userOrderService.cancelOrder(OrderId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
