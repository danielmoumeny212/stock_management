package com.daniel.gestiondestock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.gestiondestock.controllers.api.AbstractApi;
import com.daniel.gestiondestock.dto.ClientDto;
import com.daniel.gestiondestock.services.ClientService;

import static com.daniel.gestiondestock.utils.Constants.APP_ROOT;

import java.util.List;


@RestController
@RequestMapping(APP_ROOT + "/client")
public class ClientController implements AbstractApi<ClientDto>{
  
  @Autowired
  private ClientService clientService; 

  @Override
  public ClientDto create(ClientDto resource) {
    return this.clientService.save(resource);
  }

  @Override
  public void delete(Integer id) {
       this.clientService.delete(id);
  }

  @Override
  public List<ClientDto> getAll() {
    return this.clientService.findAll();
  }

  @Override
  public ClientDto getById(Integer id) {
    return this.clientService.findById(id);
  }
  
}
