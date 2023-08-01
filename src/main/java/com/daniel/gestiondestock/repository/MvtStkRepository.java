package com.daniel.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.gestiondestock.model.MvtStk;

public interface MvtStkRepository extends JpaRepository<MvtStk,Integer>{
  
}
