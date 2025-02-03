package com.meninblack.service.other;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import jakarta.annotation.PostConstruct;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    @Value("${razorpay.key}")
    private String razorpayKey;

    @Value("${razorpay.secret}")
    private String razorpaySecret;

    private RazorpayClient razorpayClient;

    @PostConstruct
    private void init() throws RazorpayException {
        razorpayClient = new RazorpayClient(razorpayKey, razorpaySecret);
    }

    public Order createOrder(Double orderValue, ObjectId orderId) throws RazorpayException {
        JSONObject options = new JSONObject();
        options.put("amount", orderValue * 100);
        options.put("currency", "INR");
        options.put("receipt", orderId);
        return razorpayClient.orders.create(options);
    }

}
