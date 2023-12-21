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
public class EmployeeService {
    private final ClientRepository clientRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public List<ShoppingCart> findAllCreatedOrders() {
         List<ShoppingCart> orders = new ArrayList<>();
        orders.addAll(shoppingCartRepository.findAllByStatus(Status.ACCEPTED));
         orders.addAll(shoppingCartRepository.findAllByStatus(Status.CREATED));
         return orders;
    }


    public void acceptOrder(Long id, Client employee) {
        ShoppingCart order = shoppingCartRepository.getById(id);
        if (order.getEmployeeId() == null) {
            order.setEmployeeId(employee.getId());
            order.setStatus(Status.ACCEPTED);
            shoppingCartRepository.save(order);
        }
    }

    public void cancelOrder(Long id, Client employee) {
        ShoppingCart order = shoppingCartRepository.getById(id);
        order.setEmployeeId(null);
        order.setStatus(Status.CREATED);
        shoppingCartRepository.save(order);
    }

    public void cookedOrder(Long id, Client employee) {
        ShoppingCart order = shoppingCartRepository.getById(id);
        order.setStatus(Status.COOKED);
        shoppingCartRepository.save(order);
    }
}
