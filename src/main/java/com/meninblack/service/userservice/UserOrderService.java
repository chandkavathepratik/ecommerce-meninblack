package com.meninblack.service.userservice;

import com.meninblack.entities.enums.order.OrderStatus;
import com.meninblack.entities.enums.payment.PaymentStatus;
import com.meninblack.entities.sub.Address;
import com.meninblack.entities.sub.CartItem;
import com.meninblack.entities.sub.Order;
import com.meninblack.entities.User;
import com.meninblack.entities.sub.Payment;
import com.meninblack.repositories.AddressRepository;
import com.meninblack.repositories.OrderRepository;
import com.meninblack.repositories.PaymentRepository;
import com.meninblack.repositories.UserRepository;
import com.meninblack.service.other.PaymentService;
import com.razorpay.RazorpayException;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class UserOrderService {

    @Autowired
    private OrderRepository oRepo;

    @Autowired
    private UserRepository uRepo;

    @Autowired
    private AddressRepository addRepo;

    @Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private PaymentService paymentService;

    @Transactional
    public com.razorpay.Order placeOrder(String addId) throws RazorpayException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = uRepo.findByUsername(username);
        Address add = addRepo.findById(new ObjectId(addId)).orElse(null);
        if(user==null) {
            log.error("Login again");
        }

        if(user!=null && add!=null && user.getAddresses().contains(add)) {
            Order order = new Order();
            order.setCustomerId(user.getUserId());
            order.setOrderItems(user.getCart());
            order.setShippingAddress(add);
            order.setCreatedAt(LocalDateTime.now());
            order.setExpectedDelivery(LocalDate.now().plusDays(6L));
            order.setPaymentStatus(PaymentStatus.Pending);

            Double orderValue = 0D;
            for(CartItem item : user.getCart()) {
                orderValue += item.getCartItemValue();
            }
            order.setOrderValue(orderValue);

            oRepo.save(order);
            user.getOrders().add(order);
            uRepo.save(user);

            String orderId = order.getOrderId().toString();
            com.razorpay.Order razorPayOrder = paymentService.createOrder(orderValue, order.getOrderId());
            order.setPaymentId(razorPayOrder.get("id"));
            order.setStatus(OrderStatus.Created);
            return razorPayOrder;
        }
        return null;
    }

    @Transactional
    public String updateOrderStatus(String paymentId, PaymentStatus status, String paymentMethod) {

        Order order = oRepo.findByPaymentId(new ObjectId(paymentId));
        if(status == PaymentStatus.Failed) {
            order.setStatus(OrderStatus.Failed);
        } else {
            order.setStatus(OrderStatus.Placed);
        }
        oRepo.save(order);

        Payment payment = new Payment(order.getPaymentId(), order.getCustomerId(), order.getOrderValue(), status, order.getCreatedAt(), order.getOrderId(), paymentMethod);
        paymentRepo.save(payment);
        return "Updated";
    }

    public Order getOrderById(String orderId) {
        ObjectId id = new ObjectId(orderId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = uRepo.findByUsername(username);
        Order order = oRepo.findById(id).orElse(null);
        if(user!=null && order!=null && user.getOrders().contains(order)) {
                return order;
        }
        return null;
    }

    public List<Order> getAllOrders() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = uRepo.findByUsername(username);
        return user.getOrders();
    }

    @Transactional
    public String cancelOrder(String orderId) {
        ObjectId id = new ObjectId(orderId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = uRepo.findByUsername(username);
        Order order = oRepo.findById(id).orElse(null);

        if(user!=null && order!=null && user.getOrders().contains(order)){
            user.getOrders().remove(order);
            oRepo.delete(order);
            return "Order cancelled";
        }
        return "Try again";
    }
}
