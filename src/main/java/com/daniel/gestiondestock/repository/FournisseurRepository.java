package com.daniel.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.daniel.gestiondestock.model.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
  
}
