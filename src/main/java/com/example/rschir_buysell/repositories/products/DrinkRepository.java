package com.example.rschir_buysell.repositories.products;

import com.example.rschir_buysell.models.products.Dish;
import com.example.rschir_buysell.models.products.Drink;
import com.example.rschir_buysell.models.products.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
    List<Drink> findByNameLike(String name);
    Page<Drink> findByNameLike(String name, Pageable pageable);
    Page<Drink> findByName(String name, Pageable pageable);
}
