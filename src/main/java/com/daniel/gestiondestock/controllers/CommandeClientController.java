package com.daniel.gestiondestock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.gestiondestock.controllers.api.AbstractApi;
import com.daniel.gestiondestock.dto.CommandeClientDto;
import com.daniel.gestiondestock.services.implementation.CommandeClientService;

import static com.daniel.gestiondestock.utils.Constants.APP_ROOT;

import java.util.List;

@RestController
@RequestMapping(APP_ROOT + "/commande/client")
public class CommandeClientController implements AbstractApi<CommandeClientDto>{
  @Autowired
  private CommandeClientService service;

  @Override
  public CommandeClientDto create(CommandeClientDto resource) {
    return  this.service.save(resource);
  }
  @Override
  public void delete(Integer id) {
     this.service.delete(id);    
  }

  @Override
  public List<CommandeClientDto> getAll() {
    return this.service.findAll();
  }

  @Override
  public CommandeClientDto getById(Integer id) {
    return this.service.findById(id);
  }
  
}
