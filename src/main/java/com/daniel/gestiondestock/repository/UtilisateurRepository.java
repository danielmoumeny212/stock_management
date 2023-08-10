package com.daniel.gestiondestock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.daniel.gestiondestock.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
  
  @Query(value = "select u  from Utilisateur u where  u.email = :email")
  Optional<Utilisateur> findUtilisateurByEmail(@Param("email") String email);

}
