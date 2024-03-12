package com.example.catalogservice.domain.catalog;

import com.example.catalogservice.domain.catalog.entity.Catalog;
import com.example.catalogservice.domain.catalog.model.response.GetCatalogRes;
import com.example.catalogservice.domain.catalog.model.response.GetCatalogsRes;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalogs")
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;
    @GetMapping
    public ResponseEntity<List<GetCatalogsRes>> getCatalogs() {
        List<Catalog> catalogs = catalogService.getCatalogs();

        List<GetCatalogsRes> getCatalogsResList = new ArrayList<GetCatalogsRes>();
        for (Catalog catalog : catalogs) {
            GetCatalogsRes catalogsRes = GetCatalogsRes.builder()
                    .id(catalog.getId())
                    .productName(catalog.getProductName())
                    .productPrice(catalog.getUnitPrice())
                    .productStock(catalog.getStock())
                    .build();
            getCatalogsResList.add(catalogsRes);
        }

        return new ResponseEntity<>(getCatalogsResList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCatalogRes> getCatalog(@PathVariable Long id) {
        Catalog catalog = catalogService.getCatalogById(id);
        GetCatalogRes getCatalogRes = GetCatalogRes.builder()
                .productName(catalog.getProductName())
                .productPrice(catalog.getUnitPrice())
                .productStock(catalog.getStock())
                .build();

        return new ResponseEntity<GetCatalogRes>(getCatalogRes, HttpStatus.OK);
    }
}
