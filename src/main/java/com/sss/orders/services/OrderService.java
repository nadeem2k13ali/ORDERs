package com.sss.orders.services;


import com.sss.orders.common.dto.OrderRequestDTO;
import com.sss.orders.common.mapper.OrderDTOtoEntityMapper;
import com.sss.orders.common.mapper.OrderEntityToOutboxEntityMapper;
import com.sss.orders.entity.Order;
import com.sss.orders.entity.Outbox;
import com.sss.orders.repositories.OrderRepository;
import com.sss.orders.repositories.OutboxRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Transactional
    public Order createOrder(OrderRequestDTO orderRequestDTO) {

        Order order = orderDTOtoEntityMapper.map(orderRequestDTO);
        order = orderRepository.save(order);

        Outbox outbox = orderEntityToOutboxEntityMapper.map(order);
        outboxRepository.save(outbox);

        return order;
    }

    public List<OrderRequestDTO> getAllOrders(){
        List<OrderRequestDTO> orderRequestDTOS = new ArrayList<>();
        List<Order> list = orderRepository.findAll();
        list.forEach(order -> {
//            Outbox outbox =orderEntityToOutboxEntityMapper.map(order);
//            orderRequestDTOS.add(outbox);
        });
        return orderRequestDTOS;
    }
}