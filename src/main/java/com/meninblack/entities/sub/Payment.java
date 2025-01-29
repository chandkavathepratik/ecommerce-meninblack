package com.meninblack.entities.sub;

import com.meninblack.entities.enums.payment.PaymentStatus;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "payments")
@Data
public class Payment {

    @Id
    private ObjectId paymentId;

    private double amount;

    private PaymentStatus status;

    private LocalDate processedAt;

    private ObjectId orderId;

}