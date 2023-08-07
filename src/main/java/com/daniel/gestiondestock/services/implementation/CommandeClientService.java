package com.daniel.gestiondestock.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.daniel.gestiondestock.dto.CommandeClientDto;
import com.daniel.gestiondestock.dto.LigneCommandeClientDto;
import com.daniel.gestiondestock.exception.EntityNotFoundException;
import com.daniel.gestiondestock.exception.ErrorCodes;
import com.daniel.gestiondestock.exception.InvalidEntityException;
import com.daniel.gestiondestock.mapper.DtoMapper;
import com.daniel.gestiondestock.model.Article;
import com.daniel.gestiondestock.model.Client;
import com.daniel.gestiondestock.model.CommandeClient;
import com.daniel.gestiondestock.model.LigneCommandeClient;
import com.daniel.gestiondestock.repository.ArticleRepository;
import com.daniel.gestiondestock.repository.ClientRepository;
import com.daniel.gestiondestock.repository.CommandeClientRepository;
import com.daniel.gestiondestock.repository.LigneCommandeClientRepository;
import com.daniel.gestiondestock.services.contracts.ICommandeClientService;
import com.daniel.gestiondestock.validators.DtoValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommandeClientService implements ICommandeClientService {

  @Autowired
  private CommandeClientRepository repository;

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private ArticleRepository articleRepository;

  @Autowired
  private LigneCommandeClientRepository ligneCommandeClientRepository;

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
      log.error("Commande Client CODE is null");
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
      log.error("Commande Client CODE is null");
      return null;
    }
    Optional<CommandeClient> commandeClient = this.repository.findById(id);
    var commande = commandeClient.orElseThrow(
        () -> new EntityNotFoundException("CommandClient Not Found", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    return DtoMapper.fromEntity(commande, CommandeClientDto.class);
  }

  @Override
  public CommandeClientDto save(CommandeClientDto dto) {
    var errors = DtoValidator.validate(dto, "ligneCommandeClient");
    List<String> articlesErrors = new ArrayList<String>();
    List<LigneCommandeClientDto> ligneCommandeClients = dto.getLigneCommandeClient();
    if (!errors.isEmpty()) {
      log.error("CommandeClient is not valid");
      throw new InvalidEntityException("Invalid CommandeClient", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
    }
    Integer clientId = dto.getClient().getId();
    Optional<Client> client = clientRepository.findById(clientId);

    if (!client.isPresent()) {
      log.error("Client with  ID {} was not found in DB", clientId);
      throw new EntityNotFoundException("Client Not Found with the corresponding ID " + clientId,
          ErrorCodes.CLIENT_NOT_FOUND);
    }
    if (ligneCommandeClients != null) {
      ligneCommandeClients.forEach(ligCmdClt -> {
        Optional<Article> article = articleRepository.findById(ligCmdClt.getArticle().getId());
        if (article.isEmpty()) {
          articlesErrors.add("Article not found with id " + ligCmdClt.getArticle().getId());
        } else {
          articlesErrors.add("Cannot register commmande client with nullable article");
        }
      });
    }
    if (!articlesErrors.isEmpty()) {
      log.warn("");
      throw new InvalidEntityException("Article Not Found in DB", ErrorCodes.ARTICLE_NOT_FOUND, errors);
    }
    var savedCommande = this.repository.save(DtoMapper.toEntity(dto, CommandeClient.class));
    ligneCommandeClients.forEach(ligCmdClt -> {
      var ligneCommandeClient = DtoMapper.toEntity(ligCmdClt, LigneCommandeClient.class);
      ligneCommandeClient.setCommandeClient(savedCommande);
      ligneCommandeClientRepository.save(ligneCommandeClient);
    });

    return DtoMapper.fromEntity(savedCommande, CommandeClientDto.class);
  }

  @Override
  public CommandeClientDto findByCode(String code) {
    if (code == null) {
      log.error("Commande Client CODE is null");
      return null;
    }
    Optional<CommandeClient> commandeClient = this.repository.findByCode(code);
    var commande = commandeClient.orElseThrow(
        () -> new EntityNotFoundException("CommandClient Not Found", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
    return DtoMapper.fromEntity(commande, CommandeClientDto.class);
  }

}
