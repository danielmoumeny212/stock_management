package com.daniel.gestiondestock.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.gestiondestock.dto.ClientDto;
import com.daniel.gestiondestock.exception.EntityNotFoundException;
import com.daniel.gestiondestock.exception.ErrorCodes;
import com.daniel.gestiondestock.exception.InvalidEntityException;
import com.daniel.gestiondestock.model.Client;
import com.daniel.gestiondestock.repository.ClientRepository;
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
        .map(ClientDto::fromEntity)
        .collect(Collectors.toList());
    return clients;
  }

  @Override
  public ClientDto findById(Integer id) {
    if (id == null) {
      log.error("Id property is null");
      return null;
    }
    Optional<Client> client = this.repository.findById(id);
    return Optional.of(
        ClientDto.fromEntity(client.get()))
        .orElseThrow(() -> new EntityNotFoundException("Aucun client avec ID  = " + id + "n'as été trouvé",
            ErrorCodes.CLIENT_NOT_FOUND));

  }

  @Override
  public ClientDto save(ClientDto dto) {
    List<String> errors = ClientValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Category is not valid");
      throw new InvalidEntityException("La category n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
    }
    return ClientDto.fromEntity(
        this.repository
            .save(
                ClientDto.toEntity(dto)));
  }

}
