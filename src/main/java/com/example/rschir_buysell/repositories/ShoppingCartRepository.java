package com.example.rschir_buysell.repositories;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.models.ShoppingCart;
import com.example.rschir_buysell.models.enums.Status;
import com.example.rschir_buysell.models.products.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart getShoppingCartByClientAndActive(Client client, Boolean active);
    List<ShoppingCart> findAllByStatus(Status status);
    List<ShoppingCart> findAllByActiveAndClient(boolean active, Client client);

    Page<ShoppingCart> findAllByAddressLikeAndActive(String address, boolean active, Pageable pageable);
    Page<ShoppingCart> findAllByActive(boolean active, Pageable pageable);
}
