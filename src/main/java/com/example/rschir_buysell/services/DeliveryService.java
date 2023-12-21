package com.example.rschir_buysell.services;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.models.ShoppingCart;
import com.example.rschir_buysell.models.enums.Status;
import com.example.rschir_buysell.repositories.ClientRepository;
import com.example.rschir_buysell.repositories.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeliveryService {
    private final ShoppingCartRepository shoppingCartRepository;

    public List<ShoppingCart> findAllCookedOrders() {
        List<ShoppingCart> orders = new ArrayList<>();
        orders.addAll(shoppingCartRepository.findAllByStatus(Status.DELIVERING));
        orders.addAll(shoppingCartRepository.findAllByStatus(Status.COOKED));
        return orders;
    }

    public void deliveringOrder(Long id, Client carrier) {
        ShoppingCart order = shoppingCartRepository.getById(id);
        if (order.getCarrierId() == null || order.getCarrierId() == 0) {
            order.setCarrierId(carrier.getId());
            order.setStatus(Status.DELIVERING);
            shoppingCartRepository.save(order);
        }
    }

    public void completeOrder(Long id) {
        ShoppingCart order = shoppingCartRepository.getById(id);
        order.setStatus(Status.COMPLETED);
        shoppingCartRepository.save(order);
    }

    public void cancelOrder(Long id) {
        ShoppingCart order = shoppingCartRepository.getById(id);
        order.setCarrierId(null);
        order.setStatus(Status.COOKED);
        shoppingCartRepository.save(order);
    }
}
