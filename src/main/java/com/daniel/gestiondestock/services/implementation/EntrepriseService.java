package com.daniel.gestiondestock.services.implementation;

import org.springframework.stereotype.Service;

import com.daniel.gestiondestock.repository.EntrepriseRepository;
import com.daniel.gestiondestock.repository.UtilisateurRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Transactional(rollbackOn = Exception.class)
@Service
@Slf4j
public class EntrepriseService {
  private EntrepriseRepository repository; 
  private UtilisateurService utilisateurService; 
  private RolesRepository rolesRepository;
  
}
