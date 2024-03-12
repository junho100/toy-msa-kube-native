package com.example.orderservice.domain.order.model.command;

import com.example.orderservice.domain.order.model.request.CreateOrderReq;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateOrderCommand {
    private Long productId;
    private Integer quantity;
    private Long userId;

    public static CreateOrderCommand from(Long userId, CreateOrderReq createOrderReq) {
        return CreateOrderCommand.builder()
                .productId(createOrderReq.getProductId())
                .quantity(createOrderReq.getQuantity())
                .userId(userId)
                .build();
    }
}
