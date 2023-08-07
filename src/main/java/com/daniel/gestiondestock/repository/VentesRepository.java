package com.daniel.gestiondestock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.daniel.gestiondestock.model.Ventes;

public interface VentesRepository extends JpaRepository<Ventes, Integer> {
  Optional<Ventes> findByCode(String code);
  
}
