package com.meninblack.service;

import com.meninblack.entities.sub.Payment;
import com.meninblack.repositories.PaymentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminPaymentService {
    @Autowired
    private PaymentRepository pRepo;

    public List<Payment> getAllPayment() {
        return pRepo.findAll();
    }

    public Payment getPayment(ObjectId paymentId) {
        return pRepo.findById(paymentId).orElse(null);
    }
}
