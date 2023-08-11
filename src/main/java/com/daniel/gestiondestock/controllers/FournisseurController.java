package com.daniel.gestiondestock.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.gestiondestock.controllers.api.FournisseurApi;
import com.daniel.gestiondestock.dto.FournisseurDto;

@RestController
@RequestMapping()
public class FournisseurController implements FournisseurApi{

  @Override
  public FournisseurDto create(FournisseurDto resource) {
    return null;
  }

  @Override
  public void delete(Integer id) {
    
  }

  @Override
  public List<FournisseurDto> getAll() {
    return null;
  }

  @Override
  public FournisseurDto getById(Integer id) {
    return null;
  }



  
}
