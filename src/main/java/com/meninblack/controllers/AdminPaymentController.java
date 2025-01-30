package com.meninblack.controllers;

import com.meninblack.entities.sub.Payment;
import com.meninblack.service.AdminPaymentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/payments")
public class AdminPaymentController {

    @Autowired
    private AdminPaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<Payment>> getPayment() {
        try {
            List<Payment> payments = paymentService.getAllPayment();
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPayment(@PathVariable ObjectId paymentId) {
        try {
            Payment pay = paymentService.getPayment(paymentId);
            return new ResponseEntity<>(pay, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
