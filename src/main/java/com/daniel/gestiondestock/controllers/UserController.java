package com.daniel.gestiondestock.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.gestiondestock.controllers.api.UtilisateurApi;
import com.daniel.gestiondestock.dto.UserDto;
import com.daniel.gestiondestock.services.implementation.UserService;
import static com.daniel.gestiondestock.utils.Constants.USERS_ENDPOINT;

@RestController
@RequestMapping(USERS_ENDPOINT)
public class UserController implements UtilisateurApi {

  @Autowired
  private UserService service;

  @Override
  public UserDto create(UserDto resource) {
    return this.service.save(resource);
  }

  @Override
  public void delete(Integer id) {
    this.service.delete(id);
  }

  @Override
  public List<UserDto> getAll() {
    return this.service.findAll();
  }

  @Override
  public UserDto getById(Integer id) {
    return this.service.findById(id);
  }

}
