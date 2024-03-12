package com.example.catalogservice.domain.catalog.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetCatalogRes {
    private String productName;
    private Integer productPrice;
    private Integer productStock;
}
