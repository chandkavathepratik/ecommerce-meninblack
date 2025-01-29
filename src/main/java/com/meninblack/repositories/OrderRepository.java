package com.meninblack.repositories;

import com.meninblack.entities.enums.order.OrderStatus;
import com.meninblack.entities.sub.Order;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, ObjectId> {

    List<Order> findByStatus(OrderStatus orderStatus);
}
