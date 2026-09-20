package Shopsphere.service;

import Shopsphere.dto.OrderRequestDTO;
import Shopsphere.dto.OrderResponseDTO;
import Shopsphere.entity.Order;
import Shopsphere.entity.User;
import Shopsphere.exception.UserNotFoundException;
import Shopsphere.repository.OrderRepository;
import Shopsphere.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(
            OrderRepository orderRepository,
            UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public OrderResponseDTO createOrder(OrderRequestDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        Order order = new Order(
                request.getTotalAmount(),
                user
        );

        Order savedOrder = orderRepository.save(order);

        return new OrderResponseDTO(
                savedOrder.getId(),
                savedOrder.getTotalAmount(),
                savedOrder.getUser().getId()
        );
    }
}