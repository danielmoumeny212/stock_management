package com.daniel.gestiondestock.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.gestiondestock.dto.FournisseurDto;
import com.daniel.gestiondestock.exception.EntityNotFoundException;
import com.daniel.gestiondestock.exception.ErrorCodes;
import com.daniel.gestiondestock.exception.InvalidEntityException;
import com.daniel.gestiondestock.exception.InvalidOperationException;
import com.daniel.gestiondestock.mapper.DtoMapper;
import com.daniel.gestiondestock.model.Fournisseur;
import com.daniel.gestiondestock.repository.CommandeFournisseurRepository;
import com.daniel.gestiondestock.repository.FournisseurRepository;
import com.daniel.gestiondestock.services.contracts.IFournisseurService;
import com.daniel.gestiondestock.validators.DtoValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FournisseurService implements IFournisseurService {

  private FournisseurRepository repository;

  private CommandeFournisseurRepository commandeFRepository;

  @Autowired
  public FournisseurService(FournisseurRepository repository, CommandeFournisseurRepository commandeFRepository) {
    this.repository = repository;
    this.commandeFRepository = commandeFRepository;
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Fournisseur ID is null");
      return;
    }
    var commandeFournisseur = commandeFRepository.findAllByFournisseurId(id);
    if (!commandeFournisseur.isEmpty()) {
      throw new InvalidOperationException("Cannot delete a fournisseur who has commands ",
          ErrorCodes.FOURNISSEUR_ALREADY_IN_USE);
    }
    this.commandeFRepository.deleteById(id);
  }

  @Override
  public List<FournisseurDto> findAll() {
    return this.repository.findAll()
        .stream()
        .map((fournisseur) -> DtoMapper.fromEntity(fournisseur, FournisseurDto.class))
        .collect(Collectors.toList());

  }

  @Override
  public FournisseurDto findById(Integer id) {
    if (id == null) {
      log.error("Fournisseur ID is null");
      return null;
    }
    return this.repository.findById(id)
        .map((fournisseur) -> DtoMapper.fromEntity(fournisseur, FournisseurDto.class))
        .orElseThrow(() -> new EntityNotFoundException("Fournisseur with id " + id + " does not exist",
            ErrorCodes.FOURNISSEUR_NOT_FOUND));
  }

  @Override
  public FournisseurDto save(FournisseurDto dto) {
    var errors = DtoValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Fournisseur is not valid {}", dto);
      throw new InvalidEntityException("Le fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
    }
    return DtoMapper.fromEntity(
        this.repository.save(DtoMapper.toEntity(dto, Fournisseur.class)), FournisseurDto.class);
  }

}
