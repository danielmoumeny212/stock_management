package com.daniel.gestiondestock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.gestiondestock.controllers.api.CommandeFournisseurApi;
import com.daniel.gestiondestock.dto.CommandeFournisseurDto;
import com.daniel.gestiondestock.services.implementation.CommandeFournisseurService;

import static com.daniel.gestiondestock.utils.Constants.COMMANDE_FOURNISSEUR_ENDPOINT;

import java.util.List;;

@RestController
@RequestMapping(COMMANDE_FOURNISSEUR_ENDPOINT)
public class CommandeFournisseurController implements CommandeFournisseurApi{
  
  @Autowired
  private CommandeFournisseurService service; 

  @Override
  public CommandeFournisseurDto create(CommandeFournisseurDto resource) {
    return this.service.save(resource);
  }

  @Override
  public void delete(Integer id) {
   this.service.delete(id);    
  }

  @Override
  public List<CommandeFournisseurDto> getAll() {
    return this.service.findAll();
  }

  @Override
  public CommandeFournisseurDto getById(Integer id) {
    return this.service.findById(id);
  }

  @Override
  public CommandeFournisseurDto findByCode(String code) {
    return this.service.findByCode(code);
  }
  
}
