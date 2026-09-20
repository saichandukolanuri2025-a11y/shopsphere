package Shopsphere.controller;

import Shopsphere.dto.OrderRequestDTO;
import Shopsphere.dto.OrderResponseDTO;
import Shopsphere.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDTO createOrder(
            @Valid @RequestBody OrderRequestDTO request) {

        return orderService.createOrder(request);
    }
}