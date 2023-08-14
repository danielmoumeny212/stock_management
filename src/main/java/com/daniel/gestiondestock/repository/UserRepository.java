package com.daniel.gestiondestock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.gestiondestock.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User>findUserByEmail(String email);

}
