package com.daniel.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.gestiondestock.model.Roles;

public interface RolesRepository  extends JpaRepository<Roles, Integer>{
  
}
