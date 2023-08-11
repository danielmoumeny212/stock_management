package com.daniel.gestiondestock.controllers;

import static com.daniel.gestiondestock.utils.Constants.ENTREPRISE_ENDPOINT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.gestiondestock.controllers.api.EntrepriseApi;
import com.daniel.gestiondestock.dto.EntrepriseDto;
import com.daniel.gestiondestock.services.implementation.EntrepriseService;;


@RestController
@RequestMapping(ENTREPRISE_ENDPOINT)
public class EntrepriseController implements EntrepriseApi{
  
  @Autowired
  private EntrepriseService service; 
  @Override
  public EntrepriseDto create(EntrepriseDto resource) {
    return this.service.save(resource);
  }

  @Override
  public void delete(Integer id) {
   this.service.delete(id);    
  }

  @Override
  public List<EntrepriseDto> getAll() {
    return this.service.findAll();
  }

  @Override
  public EntrepriseDto getById(Integer id) {
    return this.getById(id);
  }
  
}
