package com.daniel.gestiondestock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniel.gestiondestock.model.CommandeClient;
import com.daniel.gestiondestock.model.CommandeFournisseur;

@Repository
public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer>{
  Optional<CommandeFournisseur> findByCode (String code);
  List<CommandeClient> findAllByFournisseurId(Integer id);

}
