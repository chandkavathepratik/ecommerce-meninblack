package com.meninblack.repositories;

import com.meninblack.entities.sub.Payment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, ObjectId> {
}
