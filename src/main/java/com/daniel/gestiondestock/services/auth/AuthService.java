package com.daniel.gestiondestock.services.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.daniel.gestiondestock.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository repository; 
  
  private final PasswordEncoder passwordEncoder; 
  
  
}
