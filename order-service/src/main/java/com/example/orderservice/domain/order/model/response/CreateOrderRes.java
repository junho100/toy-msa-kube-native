package com.example.orderservice.domain.order.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateOrderRes {
    private Long orderId;
}
