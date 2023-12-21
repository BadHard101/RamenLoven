package com.example.rschir_buysell.services;

import com.example.rschir_buysell.models.Client;
import com.example.rschir_buysell.models.enums.Role;
import com.example.rschir_buysell.models.products.Product;
import com.example.rschir_buysell.repositories.ClientRepository;
import com.example.rschir_buysell.repositories.products.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public Client getClientByPrincipal(Principal principal) {
        if (principal == null) return new Client();
        return clientRepository.findByEmail(principal.getName());
    }

    public Client getClientById(Long id) {
        return clientRepository.getById(id);
    }

    public List<Client> findAllClients(String email) {
        List<Client> clients = new ArrayList<>();
        if (email != null) {
            clients.add(clientRepository.findByEmail(email));
        } else {
            clients = clientRepository.findAll();
        }
        return clients;
    }

    public void banUser(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client != null) {
            if (client.isActive()) {
                client.setActive(false);
                log.info("Ban user with id = {}; email: {}", client.getId(), client.getEmail());
            } else {
                client.setActive(true);
                log.info("Unban user with id = {}; email: {}", client.getId(), client.getEmail());
            }
            clientRepository.save(client);
        }
    }

    public void changeUserRole(Client user, String role) {
        Set<Role> roles = new HashSet<>();
        if (role != null && !role.isEmpty()) {
            roles.add(Role.valueOf(role));
        }
        user.setRoles(roles);
        clientRepository.save(user);
    }

    public void deleteProduct(Long id) {
        productRepository.delete(productRepository.getById(id));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
