package com.daniel.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.gestiondestock.model.LigneCommandeFournisseur;

public interface LigneCommandeFournisseurRepository extends JpaRepository<LigneCommandeFournisseur, Integer> {
  
}
