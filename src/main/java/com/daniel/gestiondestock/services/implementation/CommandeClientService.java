package com.daniel.gestiondestock.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.daniel.gestiondestock.dto.CommandeClientDto;
import com.daniel.gestiondestock.exception.EntityNotFoundException;
import com.daniel.gestiondestock.exception.ErrorCodes;
import com.daniel.gestiondestock.exception.InvalidEntityException;
import com.daniel.gestiondestock.mapper.DtoMapper;
import com.daniel.gestiondestock.model.CommandeClient;
import com.daniel.gestiondestock.repository.CommandeClientRepository;
import com.daniel.gestiondestock.services.contracts.ICommandeClientService;
import com.daniel.gestiondestock.validators.DtoValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommandeClientService implements ICommandeClientService {

  @Autowired
  private CommandeClientRepository repository;

  // @Override
  // public CommandeClientDto findCommandeByDate(String date) {
  //   if (!StringUtils.hasLength(date)) {
  //     log.error("Invalid date: " + date);
  //     return null;
  //   }
  //   Optional<CommandeClient> commandeOptional = this.repository.findByDateCommande(date);
  //   var commande = commandeOptional.orElseThrow(
  //       () -> new EntityNotFoundException("Aucune commande client n'as été trouvé",
  //           ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));

  //   return DtoMapper.fromEntity(commande, CommandeClientDto.class);

  // }

  @Override
  public CommandeClientDto findByIdEntreprise(String idEntreprise) {
    if (!StringUtils.hasLength(idEntreprise)) {
      log.error("Invalid date: " + idEntreprise);
      return null;
    }
    Optional<CommandeClient> commandeOptional = this.repository.findByIdEntreprise(idEntreprise);
    var commande = commandeOptional.orElseThrow(
        () -> new EntityNotFoundException("CommandeClient Not Found",
            ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    return DtoMapper.fromEntity(commande, CommandeClientDto.class);
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Invalid ID passed ");
      return;
    }
    this.repository.deleteById(id);
  }

  @Override
  public List<CommandeClientDto> findAll() {
    List<CommandeClientDto> commandeClientDtos = this.repository.findAll()
        .stream()
        .map((commande) -> DtoMapper.fromEntity(commande, CommandeClientDto.class))
        .collect(Collectors.toList());
    return commandeClientDtos;
  }

  @Override
  public CommandeClientDto findById(Integer id) {
    if (id == null) {
      log.error("Invalid id provide ");
      return null;
    }
    Optional<CommandeClient> commandeClient = this.repository.findById(id);
    var commande = commandeClient.orElseThrow(
        () -> new EntityNotFoundException("CommandClient Not Found", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    return DtoMapper.fromEntity(commande, CommandeClientDto.class);
  }

  @Override
  public CommandeClientDto save(CommandeClientDto dto) {
   List<String> errors = DtoValidator.validate(dto);
   if(!errors.isEmpty()){
     log.error("CommandeClient is not valid");
      throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID,errors);
   }
   var commande = DtoMapper.toEntity(dto, CommandeClient.class);
    return DtoMapper.fromEntity(this.repository.save(commande), CommandeClientDto.class);
  }

}
