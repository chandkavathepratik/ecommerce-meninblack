package com.meninblack.entities.sub;

import com.meninblack.entities.enums.order.OrderStatus;
import com.meninblack.entities.enums.payment.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    private ObjectId orderId;

    @NonNull
    private ObjectId customerId;

    private List<OrderItem> orderItems;

    @NonNull
    private Double orderValue;

    @NonNull
    private Address shippingAddress;

    @NonNull
    private PaymentStatus paymentStatus;

    private ObjectId paymentId;

    @NonNull
    private LocalDateTime placedAt;

    @NonNull
    private OrderStatus status;

    @NonNull
    private String expectedDelivery;

}