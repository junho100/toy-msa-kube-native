package com.example.orderservice.domain.order;

import com.example.orderservice.common.client.CatalogServiceClient;
import com.example.orderservice.common.client.model.response.GetCatalogRes;
import com.example.orderservice.domain.order.entity.Order;
import com.example.orderservice.domain.order.model.command.CreateOrderCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CatalogServiceClient catalogServiceClient;

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order createOrder(CreateOrderCommand createOrderCommand) {
        GetCatalogRes getCatalogRes = catalogServiceClient.getCatalogByCatalogId(createOrderCommand.getProductId());

        Integer totalPrice = getCatalogRes.getProductPrice() * createOrderCommand.getQuantity();

        Order order = Order.builder()
                .productId(createOrderCommand.getProductId())
                .quantity(createOrderCommand.getQuantity())
                .totalPrice(totalPrice)
                .userId(createOrderCommand.getUserId())
                .build();

        return orderRepository.save(order);
    }
}
