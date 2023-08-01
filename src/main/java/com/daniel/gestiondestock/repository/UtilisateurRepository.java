package com.daniel.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.daniel.gestiondestock.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
  
}
