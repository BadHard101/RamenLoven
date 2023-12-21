package com.example.rschir_buysell.repositories;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart getShoppingCartByClientAndActive(Client client, Boolean active);
    List<ShoppingCart> findAllByClientAndActive(Client client, Boolean active);
}
