package com.daniel.gestiondestock.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.gestiondestock.dto.VentesDto;
import com.daniel.gestiondestock.exception.EntityNotFoundException;
import com.daniel.gestiondestock.exception.ErrorCodes;
import com.daniel.gestiondestock.exception.InvalidEntityException;
import com.daniel.gestiondestock.mapper.DtoMapper;
import com.daniel.gestiondestock.model.Article;
import com.daniel.gestiondestock.model.LigneVente;
import com.daniel.gestiondestock.model.Ventes;
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
    if (id == null) {
      log.error("Ventes ID is NULL");
      return null;
    }
    return this.repository.findById(id)
        .map((vente) -> DtoMapper.fromEntity(vente, VentesDto.class))
        .orElseThrow(() -> new EntityNotFoundException("Vente Not Found in BDD", ErrorCodes.VENTE_NOT_FOUND));
  }

  @Override
  public VentesDto save(VentesDto dto) {
    var errors = DtoValidator.validate(dto);
    List<String> articleErrors = new ArrayList<String>();
    if (!errors.isEmpty()) {
      log.error("Invalid Vente ");
      throw new InvalidEntityException("Invalid Ventes", ErrorCodes.VENTE_NOT_VALID, errors);
    }
    dto.getLigneVentes().forEach((ligneVenteDto) -> {
      Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
      if (article.isEmpty())
        articleErrors.add("Article not found for id " + ligneVenteDto.getArticle().getId());
    });

    if (!articleErrors.isEmpty()) {
      log.error("One or more articles were not found in the DB ");
      throw new InvalidEntityException("One or more articles were not found in BDD", ErrorCodes.VENTE_NOT_FOUND,
          errors);
    }
    var savedVentes = this.repository.save(DtoMapper.toEntity(dto, Ventes.class));
    dto.getLigneVentes().forEach(ligneVenteDto -> {
      LigneVente ligneVente = DtoMapper.toEntity(ligneVenteDto, LigneVente.class);
      ligneVente.setVente(savedVentes);
      ligneVenteRepository.save(ligneVente);
    });
    return DtoMapper.fromEntity(savedVentes, VentesDto.class);
  }

}
