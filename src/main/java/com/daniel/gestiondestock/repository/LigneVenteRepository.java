package com.daniel.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.gestiondestock.model.LigneVente;

public interface LigneVenteRepository extends JpaRepository<LigneVente, Integer> {
  
}
