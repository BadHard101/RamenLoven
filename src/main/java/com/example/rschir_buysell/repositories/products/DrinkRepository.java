package com.example.rschir_buysell.repositories.products;

import com.example.rschir_buysell.models.products.Drink;
import com.example.rschir_buysell.models.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
    Product findByName(String name);
}
