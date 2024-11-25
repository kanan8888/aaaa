package az.myecommerceapp.controller;


import az.myecommerceapp.dto.OrderDto;
import az.myecommerceapp.entity.Order;
import az.myecommerceapp.service.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<Order> create(@RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderServiceImpl.createOrder(orderDto), HttpStatus.CREATED);
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderServiceImpl.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/getAllOrdersUserById/{id}")
    public ResponseEntity<List<Order>> getAllOrdersByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(orderServiceImpl.getAllOrdersUserById(id), HttpStatus.OK);
    }




}
