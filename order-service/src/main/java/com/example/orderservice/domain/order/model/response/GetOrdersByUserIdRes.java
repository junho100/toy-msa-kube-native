package com.example.orderservice.domain.order.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetOrdersByUserIdRes {
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private Integer totalPrice;
}
