package com.example.rschir_buysell.repositories;

import com.example.rschir_buysell.models.Image;
import com.example.rschir_buysell.models.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    void deleteByProductId(Long product_id);
}
