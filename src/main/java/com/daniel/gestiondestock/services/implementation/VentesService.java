package com.daniel.gestiondestock.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.gestiondestock.dto.VentesDto;
import com.daniel.gestiondestock.exception.ErrorCodes;
import com.daniel.gestiondestock.exception.InvalidEntityException;
import com.daniel.gestiondestock.repository.ArticleRepository;
import com.daniel.gestiondestock.repository.LigneVenteRepository;
import com.daniel.gestiondestock.repository.VentesRepository;
import com.daniel.gestiondestock.services.contracts.IVentesService;
import com.daniel.gestiondestock.validators.DtoValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VentesService implements IVentesService {

  private VentesRepository repository;
  private ArticleRepository articleRepository;
  private LigneVenteRepository ligneVenteRepository;

  @Autowired
  public VentesService(VentesRepository repository, ArticleRepository articleRepository,
      LigneVenteRepository ligneVenteRepository) {
    this.repository = repository;
    this.articleRepository = articleRepository;
    this.ligneVenteRepository = ligneVenteRepository;
  }

  @Override
  public VentesDto findByCode(String code) {
   
    return null;
  }

  @Override
  public void delete(Integer id) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<VentesDto> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public VentesDto findById(Integer id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public VentesDto save(VentesDto dto) {
    var errors = DtoValidator.validate(dto);
    if(!errors.isEmpty()){
      log.error("Invalid Vente ");
      throw new InvalidEntityException("Invalid Ventes", ErrorCodes.VENTE_NOT_VALID, errors);
    }
    return null;
  }

}
