package com.example.catalogservice.domain.catalog;

import com.example.catalogservice.domain.catalog.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Long>{
}
