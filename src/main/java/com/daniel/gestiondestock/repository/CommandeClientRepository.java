package com.daniel.gestiondestock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.gestiondestock.model.CommandeClient;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer>{
  Optional<CommandeClient> findByIdEntreprise(Integer idEntreprise);
  Optional<CommandeClient> findByCode(String code); 
  
}
