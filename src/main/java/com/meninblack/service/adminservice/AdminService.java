package com.meninblack.service.adminservice;

import com.meninblack.entities.User;
import com.meninblack.entities.enums.order.OrderStatus;
import com.meninblack.entities.sub.Order;
import com.meninblack.repositories.OrderRepository;
import com.meninblack.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private OrderRepository oRepo;

    @Autowired
    private UserRepository uRepo;

    public List<User> getAllUserDetails() {
        return uRepo.findAll();
    }

    public List<Order> getAllOrders() {
        return oRepo.findAll();
    }

    public List<Order> getOrdersHistory() {
        return oRepo.findByStatus(OrderStatus.Delivered);
    }

    public List<Order> getAllPlacedOrders() {
        return oRepo.findByStatus(OrderStatus.Placed);
    }

    public Order getOrderById(String orderId) {
        return oRepo.findById(new ObjectId(orderId)).orElse(null);
    }

}
