package com.daniel.gestiondestock.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.gestiondestock.controllers.api.AbstractApi;
import com.daniel.gestiondestock.dto.ClientDto;

import static com.daniel.gestiondestock.utils.Constants.APP_ROOT;

import java.util.List;


@RestController
@RequestMapping(APP_ROOT + "/client")
public class ClientController implements AbstractApi<ClientDto>{

  @Override
  public ClientDto create(ClientDto resource) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(Integer id) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public List<ClientDto> getAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ClientDto getById(Integer id) {
    // TODO Auto-generated method stub
    return null;
  }
  
}
