package com.daniel.gestiondestock.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.gestiondestock.dto.ClientDto;
import com.daniel.gestiondestock.exception.EntityNotFoundException;
import com.daniel.gestiondestock.exception.ErrorCodes;
import com.daniel.gestiondestock.exception.InvalidEntityException;
import com.daniel.gestiondestock.mapper.DtoMapper;
import com.daniel.gestiondestock.model.Client;
import com.daniel.gestiondestock.repository.ClientRepository;
import com.daniel.gestiondestock.services.contracts.AbstractService;
import com.daniel.gestiondestock.validators.ClientValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientService implements AbstractService<ClientDto> {

  @Autowired
  private ClientRepository repository;

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Id property is null");
      return;
    }
    this.repository.deleteById(id);

  }

  @Override
  public List<ClientDto> findAll() {
    List<ClientDto> clients = this.repository.findAll()
        .stream()
        .map((client) -> DtoMapper.fromEntity(client, ClientDto.class))
        .collect(Collectors.toList());
    return clients;
  }

  @Override
  public ClientDto findById(Integer id) {
    if (id == null) {
      log.error("Id property is null");
      return null;
    }

    Optional<Client> clientOptional = this.repository.findById(id);
    Client client = clientOptional
        .orElseThrow(() -> new EntityNotFoundException("Aucun client avec ID = " + id + " n'a été trouvé",
            ErrorCodes.CLIENT_NOT_FOUND));
    return DtoMapper.fromEntity(client, ClientDto.class);
  }

  @Override
  public ClientDto save(ClientDto dto) {
    List<String> errors = ClientValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Client is not valid");
      throw new InvalidEntityException("La category n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
    }
    Client client= DtoMapper.toEntity(dto, Client.class);
    var savedClient = this.repository.save(client);
    return DtoMapper.fromEntity(savedClient, ClientDto.class);
  }

}
