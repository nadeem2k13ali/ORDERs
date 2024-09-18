package com.sss.orders.common.mapper;


import com.sss.orders.common.dto.OrderRequestDTO;
import com.sss.orders.entity.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderDTOtoEntityMapper {


    public Order map(OrderRequestDTO orderRequestDTO) {
        return
                Order.builder()
                        .customerId(orderRequestDTO.getCustomerId())
                        .name(orderRequestDTO.getName())
                        .productType(orderRequestDTO.getProductType())
                        .quantity(orderRequestDTO.getQuantity())
                        .price(orderRequestDTO.getPrice())
                        .orderDate(new Date())
                        .build();
    }
    public OrderRequestDTO map(Order order) {
        return
                OrderRequestDTO.builder()
                        .customerId(order.getCustomerId())
                        .name(order.getName())
                        .productType(order.getProductType())
                        .quantity(order.getQuantity())
                        .price(order.getPrice())
//                        .orderDate(order.getOrderDate())
                        .build();
    }

}