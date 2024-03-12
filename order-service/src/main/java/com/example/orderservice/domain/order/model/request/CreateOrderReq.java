package com.example.orderservice.domain.order.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateOrderReq {
    private Long productId;
    private Integer quantity;
}
