package com.meninblack.entities.sub;

import com.meninblack.entities.enums.payment.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    private ObjectId paymentId;

    @NonNull
    private ObjectId userId;

    @NonNull
    private Double amount;

    @NonNull
    private PaymentStatus status;

    @NonNull
    private LocalDateTime processedAt;

    @NonNull
    private ObjectId orderId;

    @NonNull
    private String paymentMethod;

}