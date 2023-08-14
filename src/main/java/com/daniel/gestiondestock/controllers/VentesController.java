package com.daniel.gestiondestock.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.gestiondestock.controllers.api.VentesApi;
import com.daniel.gestiondestock.dto.VentesDto;
import com.daniel.gestiondestock.services.implementation.VentesService;
import static com.daniel.gestiondestock.utils.Constants.VENTES_ENDPOINT;;

@RestController
@RequestMapping(VENTES_ENDPOINT)
public class VentesController implements VentesApi {

  @Autowired
  private VentesService service;

  @Override
  public VentesDto create(VentesDto resource) {
    return this.service.save(resource);
  }

  @Override
  public void delete(Integer id) {
    this.service.delete(id);
  }

  @Override
  public List<VentesDto> getAll() {
    return this.service.findAll();
  }

  @Override
  public VentesDto getById(Integer id) {
    return this.service.findById(id);
  }

}
