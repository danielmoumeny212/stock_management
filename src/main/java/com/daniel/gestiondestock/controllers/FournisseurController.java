package com.daniel.gestiondestock.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.gestiondestock.controllers.api.FournisseurApi;
import com.daniel.gestiondestock.dto.FournisseurDto;
import com.daniel.gestiondestock.services.implementation.FournisseurService;
import static com.daniel.gestiondestock.utils.Constants.FOURNISSEUR_ENDPOINT;;

@RestController
@RequestMapping(FOURNISSEUR_ENDPOINT)
public class FournisseurController implements FournisseurApi{
  
  @Autowired
  private FournisseurService  service; 

  @Override
  public FournisseurDto create(FournisseurDto resource) {
    return this.service.save(resource);
  }

  @Override
  public void delete(Integer id) {
   this.service.delete(id);
  }

  @Override
  public List<FournisseurDto> getAll() {
    return this.service.findAll();
  }

  @Override
  public FournisseurDto getById(Integer id) {
    return this.service.findById(id);
  }



  
}
