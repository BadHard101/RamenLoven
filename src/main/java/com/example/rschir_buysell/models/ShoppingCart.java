package com.example.rschir_buysell.models;

import com.example.rschir_buysell.models.products.Product;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table
@Data
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "client")
    private Client client;

    @Column(name = "is_active")
    private boolean active;


    private LocalDateTime creationDate;


    @ElementCollection
    @CollectionTable(name = "cart_items", joinColumns = @JoinColumn(name = "cart_id"))
    private Map<Product, Integer> items = new HashMap<>();

    public void addItem(Product product) {
        items.put(product, items.getOrDefault(product, 0) + 1);
    }

    public void removeItem(Product product) {
        int quantity = items.getOrDefault(product, 0);
        if (quantity > 1) {
            items.put(product, quantity - 1);
        } else {
            items.remove(product);
        }
    }

    public void deleteItem(Product product) {
        items.remove(product);
    }

    public Double calculateTotal() {
        Double total = 0.0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }
}
