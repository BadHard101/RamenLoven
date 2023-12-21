package com.example.rschir_buysell.repositories;

import com.example.rschir_buysell.models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
    Page<Client> findByEmail(String email, Pageable pageable);
    Page<Client> findByEmailLike(String partialEmail, Pageable pageable);
}
