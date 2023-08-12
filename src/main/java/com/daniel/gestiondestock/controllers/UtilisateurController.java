package com.daniel.gestiondestock.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.gestiondestock.controllers.api.UtilisateurApi;
import com.daniel.gestiondestock.dto.UtilisateurDto;
import com.daniel.gestiondestock.services.implementation.UtilisateurService;
import static com.daniel.gestiondestock.utils.Constants.USERS_ENDPOINT;

@RestController
@RequestMapping(USERS_ENDPOINT)
public class UtilisateurController implements UtilisateurApi {
  
  @Autowired
  private UtilisateurService service; 

  @Override
  public UtilisateurDto create(UtilisateurDto resource) {
    return this.service.save(resource);
  }

  @Override
  public void delete(Integer id) {
    this.service.delete(id);    
  }

  @Override
  public List<UtilisateurDto> getAll() {
    return this.service.findAll();
  }

  @Override
  public UtilisateurDto getById(Integer id) {
    return this.service.findById(id);
  }
  
}
