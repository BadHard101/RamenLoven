package com.example.rschir_buysell.repositories.products;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.models.products.Dish;
import com.example.rschir_buysell.models.products.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByNameLike(String name);
    Page<Dish> findByNameLike(String name, Pageable pageable);
    Page<Dish> findByName(String name, Pageable pageable);
}
