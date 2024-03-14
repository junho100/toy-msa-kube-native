package com.example.orderservice.common.client;

import com.example.orderservice.common.client.model.response.GetCatalogRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalog-service", url = "${spring.cloud.openfeign.service.catalog}")
public interface CatalogServiceClient {

    @GetMapping("/catalogs/{catalogId}")
    GetCatalogRes getCatalogByCatalogId(@PathVariable Long catalogId);
}
