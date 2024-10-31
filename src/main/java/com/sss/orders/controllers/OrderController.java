package com.sss.orders.controllers;

import com.sss.orders.common.dto.OrderRequestDTO;
import com.sss.orders.entity.Order;
import com.sss.orders.services.OrderService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/hello")
    String sayHello(){
        return "Hello welcome";
    }
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO orderRequest) {
        Order createdOrder = orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order orders = orderService.getOrder(id);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
/*    @GetMapping
    public ResponseEntity<Order> getOrderByIdT(@RequestParam Long id) {
        Order orders = orderService.getOrder(id);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }*/

    @GetMapping
    public ResponseEntity<List<OrderRequestDTO>> getAllOrder() {
        List<OrderRequestDTO> orders = orderService.getAllOrders();
        return ResponseEntity.status(HttpStatus.CREATED).body(orders);
    }
}
