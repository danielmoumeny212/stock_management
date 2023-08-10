package com.daniel.gestiondestock.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.gestiondestock.mapper.DtoMapper;
import com.daniel.gestiondestock.dto.EntrepriseDto;
import com.daniel.gestiondestock.exception.EntityNotFoundException;
import com.daniel.gestiondestock.exception.ErrorCodes;
import com.daniel.gestiondestock.repository.EntrepriseRepository;
import com.daniel.gestiondestock.repository.RolesRepository;
import com.daniel.gestiondestock.services.contracts.IEntrepriseService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Transactional(rollbackOn = Exception.class)
@Service
@Slf4j
public class EntrepriseService implements IEntrepriseService {
  private EntrepriseRepository repository;
  private UtilisateurService utilisateurService;
  private RolesRepository rolesRepository;

  @Autowired
  public EntrepriseService(EntrepriseRepository repository, UtilisateurService utilisateurService,
      RolesRepository rolesRepository) {
    this.repository = repository;
    this.utilisateurService = utilisateurService;
    this.rolesRepository = rolesRepository;
  }
@Override
  public EntrepriseDto save(EntrepriseDto dto) {
    return null;
  }


  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Entreprise ID is null !");
    }
    this.repository.deleteById(id);

  }

  @Override
  public List<EntrepriseDto> findAll() {
    return this.repository.findAll()
        .stream()
        .map((instance) -> DtoMapper.fromEntity(instance, EntrepriseDto.class))
        .collect(Collectors.toList());
  }

  @Override
  public EntrepriseDto findById(Integer id) {
    if (id == null) {
      log.error("Entreprise ID is null");
      return null;
    }
    return this.repository.findById(id)
        .map((instance) -> DtoMapper.fromEntity(instance, EntrepriseDto.class))
        .orElseThrow(() -> new EntityNotFoundException("There's not entreprise with ID  = " + id,
            ErrorCodes.ENTREPRISE_NOT_FOUND));
  }

  

}
