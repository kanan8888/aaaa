package az.myecommerceapp.service;

import az.myecommerceapp.dto.OrderDto;
import az.myecommerceapp.entity.Order;
import az.myecommerceapp.entity.User;
import az.myecommerceapp.enums.OrderStatus;
import az.myecommerceapp.repository.OrderRepository;
import az.myecommerceapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public Order createOrder(OrderDto orderDto) {
        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = modelMapper.map(orderDto, Order.class);
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING); // Default status

        orderRepository.save(order);
        user.getOrders().add(order);
        userRepository.save(user);

        return order;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getAllOrdersUserById(Long id) {
        return orderRepository.findAllByUserId(id);
    }




}
