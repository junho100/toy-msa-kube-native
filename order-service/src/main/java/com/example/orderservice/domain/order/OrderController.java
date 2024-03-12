package com.example.orderservice.domain.order;

import com.example.orderservice.domain.order.entity.Order;
import com.example.orderservice.domain.order.model.command.CreateOrderCommand;
import com.example.orderservice.domain.order.model.request.CreateOrderReq;
import com.example.orderservice.domain.order.model.response.CreateOrderRes;
import com.example.orderservice.domain.order.model.response.GetOrdersByUserIdRes;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<GetOrdersByUserIdRes>> getOrdersByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);

        List<GetOrdersByUserIdRes> getOrdersByUserIdResList = new ArrayList<GetOrdersByUserIdRes>();
        for (Order order : orders) {
            GetOrdersByUserIdRes getOrdersByUserIdRes = GetOrdersByUserIdRes.builder()
                    .orderId(order.getId())
                    .productId(order.getProductId())
                    .quantity(order.getQuantity())
                    .totalPrice(order.getTotalPrice())
                    .build();
            getOrdersByUserIdResList.add(getOrdersByUserIdRes);
        }

        return new ResponseEntity<>(getOrdersByUserIdResList, HttpStatus.OK);
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<CreateOrderRes> createOrder(@PathVariable Long userId, @RequestBody CreateOrderReq createOrderReq) {
        CreateOrderCommand createOrderCommand = CreateOrderCommand.from(userId, createOrderReq);
        Order createdOrder = orderService.createOrder(createOrderCommand);

        CreateOrderRes createOrderRes = CreateOrderRes.builder()
            .orderId(createdOrder.getId())
            .build();

        return new ResponseEntity<CreateOrderRes>(createOrderRes, HttpStatus.CREATED);
    }
}
