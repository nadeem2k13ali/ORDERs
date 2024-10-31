package com.sss.orders.services;


import com.sss.orders.common.dto.OrderRequestDTO;
import com.sss.orders.common.mapper.OrderDTOtoEntityMapper;
import com.sss.orders.common.mapper.OrderEntityToOutboxEntityMapper;
import com.sss.orders.entity.Order;
import com.sss.orders.entity.Outbox;
import com.sss.orders.repositories.OrderRepository;
import com.sss.orders.repositories.OutboxRepository;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDTOtoEntityMapper orderDTOtoEntityMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OutboxRepository outboxRepository;

    @Autowired
    private OrderEntityToOutboxEntityMapper orderEntityToOutboxEntityMapper;

//    @Cacheable(value = "orders", key = "#orderId")
    @Transactional
    public Order getOrder(long id) {
        simulateSlowService();
        Order order = orderRepository.getById(id);

        Outbox outbox = orderEntityToOutboxEntityMapper.map(order);
        outboxRepository.save(outbox);
        return order;
    }

    @Transactional
    public Order createOrder(OrderRequestDTO orderRequestDTO) {
        Order order = orderDTOtoEntityMapper.map(orderRequestDTO);
        order = orderRepository.save(order);

        Outbox outbox = orderEntityToOutboxEntityMapper.map(order);
        outboxRepository.save(outbox);

        return order;
    }
    private void simulateSlowService() {
        try {
            Thread.sleep(3000L); // Simulate slow service
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<OrderRequestDTO> getAllOrders(){
        List<OrderRequestDTO> orderRequestDTOS = new ArrayList<>();
        List<Order> list = orderRepository.findAll();
        for(Order order:list){
            orderRequestDTOS.add(orderDTOtoEntityMapper.map(order));
        }
        return orderRequestDTOS;
    }
}