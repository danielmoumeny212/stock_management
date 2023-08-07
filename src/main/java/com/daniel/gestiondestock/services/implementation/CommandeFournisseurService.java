package com.daniel.gestiondestock.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.gestiondestock.dto.CommandeFournisseurDto;
import com.daniel.gestiondestock.exception.EntityNotFoundException;
import com.daniel.gestiondestock.exception.ErrorCodes;
import com.daniel.gestiondestock.mapper.DtoMapper;
import com.daniel.gestiondestock.model.CommandeFournisseur;
import com.daniel.gestiondestock.repository.CommandeFournisseurRepository;
import com.daniel.gestiondestock.services.contracts.ICommandeFournisseurService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommandeFournisseurService implements ICommandeFournisseurService {

  @Autowired
  private CommandeFournisseurRepository repository;

  @Override
  public CommandeFournisseurDto findByCode(String code) {
    if (code == null) {
      log.error("CommandeFournisseur with null CODE is not allowed");
      return null;

    }
    Optional<CommandeFournisseur> commandeFournisseurOptional = this.repository.findByCode(code);
    var commandeFournisseur = commandeFournisseurOptional.orElseThrow(
        () -> new EntityNotFoundException("CommandeFournisseur not Found", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
    return DtoMapper.fromEntity(commandeFournisseur, CommandeFournisseurDto.class);
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("CommandeFournisseur with null id is not allowed");
    }
    this.repository.deleteById(id);

  }

  @Override
  public List<CommandeFournisseurDto> findAll() {
    List<CommandeFournisseurDto> commandeFournisseurs = this.repository.findAll()
        .stream()
        .map((cmdFournisseur) -> DtoMapper.fromEntity(cmdFournisseur, CommandeFournisseurDto.class))
        .collect(Collectors.toList());
    return commandeFournisseurs;
  }

  @Override
  public CommandeFournisseurDto findById(Integer id) {
    if (id == null) {
      log.error("CommandeFournisseur with null id is not allowed");
      return null;
    }
    Optional<CommandeFournisseur> commandeFournisseurOptional = this.repository.findById(id);
    var commandeFournisseur = commandeFournisseurOptional.orElseThrow(
        () -> new EntityNotFoundException("Commande Fournisseur not found ",
            ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
    return DtoMapper.fromEntity(commandeFournisseur, CommandeFournisseurDto.class);
  }

  @Override
  public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
    // TODO Auto-generated method stub
    return null;
  }

}
