package com.meninblack.entities.sub;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    private double amount;

    private String status;

    private LocalDateTime processedAt;

    private ObjectId orderId;

    private String paymentMethod;

}