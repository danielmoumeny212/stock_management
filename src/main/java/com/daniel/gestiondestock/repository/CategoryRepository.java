package com.daniel.gestiondestock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.gestiondestock.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
  Optional<Category> findCategoryBycode(String code);
  
}
