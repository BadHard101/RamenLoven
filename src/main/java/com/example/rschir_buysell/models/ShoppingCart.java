package com.example.rschir_buysell.models;

import com.example.rschir_buysell.models.enums.Status;
import com.example.rschir_buysell.models.products.Product;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    @ElementCollection
    @CollectionTable(name = "cart_items", joinColumns = @JoinColumn(name = "cart_id"))
    private Map<Product, Integer> items = new HashMap<>();


    @Column(name = "address")
    private String address;

    @Column(name = "employee_id")
    private Long employeeId = (long) 0;

    @Column(name = "carrier_id")
    private Long carrierId = (long) 0;

    @ElementCollection(targetClass = Status.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "order_status",
            joinColumns = @JoinColumn(name = "order_id"))
    @Enumerated(EnumType.STRING)
    private Set<Status> status = new HashSet<>();

    @Column
    private LocalDateTime creationDate;

    public void setStatus(Status status) {
        this.status.clear();
        this.status.add(status);
    }

    public boolean isCreated() {
        if (status.contains(Status.CREATED)) return true;
        return false;
    }

    public boolean isCooked() {
        if (status.contains(Status.COOKED)) return true;
        return false;
    }

    public boolean isAccepted() {
        if (status.contains(Status.ACCEPTED)) return true;
        return false;
    }

    public boolean isDelivering() {
        if (status.contains(Status.DELIVERING)) return true;
        return false;
    }

    public boolean isCompleted() {
        if (status.contains(Status.COMPLETED)) return true;
        return false;
    }

    public boolean isCanceled() {
        if (status.contains(Status.CANCELED)) return true;
        return false;
    }

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
