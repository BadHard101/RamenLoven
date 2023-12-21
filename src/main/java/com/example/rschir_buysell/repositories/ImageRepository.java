package com.example.rschir_buysell.repositories;

import com.example.rschir_buysell.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
