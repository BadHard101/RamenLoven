package com.example.rschir_buysell.services;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.models.ShoppingCart;
import com.example.rschir_buysell.models.enums.Role;
import com.example.rschir_buysell.models.products.Product;
import com.example.rschir_buysell.repositories.ClientRepository;
import com.example.rschir_buysell.repositories.ShoppingCartRepository;
import com.example.rschir_buysell.repositories.products.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public boolean createClient(Client client) {
        String email = client.getEmail();
        if (clientRepository.findByEmail(email) != null) return false;
        client.setActive(true);
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        client.getRoles().add(Role.ROLE_USER);
        log.info("Saving new User with email: {}", email);
        clientRepository.save(client);
        return true;
    }

    public Client getClientByPrincipal(Principal principal) {
        if (principal == null) return new Client();
        return clientRepository.findByEmail(principal.getName());
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<ShoppingCart> getHistory(Client client) {
       return shoppingCartRepository.findAllByActiveAndClient(false, client);
    }
}
