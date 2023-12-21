package com.example.rschir_buysell.repositories.products;

import com.example.rschir_buysell.models.products.Dish;
import com.example.rschir_buysell.models.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
    Product findByName(String name);
}
