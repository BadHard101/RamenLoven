package com.example.rschir_buysell.repositories;

import com.example.rschir_buysell.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
}
