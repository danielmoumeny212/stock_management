package com.daniel.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.gestiondestock.model.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer>{
  
  
}
