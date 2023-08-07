package com.daniel.gestiondestock.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.gestiondestock.dto.CommandeFournisseurDto;
import com.daniel.gestiondestock.exception.EntityNotFoundException;
import com.daniel.gestiondestock.exception.ErrorCodes;
import com.daniel.gestiondestock.exception.InvalidEntityException;
import com.daniel.gestiondestock.mapper.DtoMapper;
import com.daniel.gestiondestock.model.Article;
import com.daniel.gestiondestock.model.CommandeFournisseur;
import com.daniel.gestiondestock.model.Fournisseur;
import com.daniel.gestiondestock.model.LigneCommandeFournisseur;
import com.daniel.gestiondestock.repository.ArticleRepository;
import com.daniel.gestiondestock.repository.CommandeFournisseurRepository;
import com.daniel.gestiondestock.repository.FournisseurRepository;
import com.daniel.gestiondestock.repository.LigneCommandeFournisseurRepository;
import com.daniel.gestiondestock.services.contracts.ICommandeFournisseurService;
import com.daniel.gestiondestock.validators.DtoValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommandeFournisseurService implements ICommandeFournisseurService {

  @Autowired
  private CommandeFournisseurRepository repository;

  @Autowired
  private FournisseurRepository fournisseurRepository;

  @Autowired
  private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;

  @Autowired
  private ArticleRepository articleRepository;

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
    var errors = DtoValidator.validate(dto);
    var ligneCommandeFournisseurs = dto.getLigneCommandeFournisseurs();

    if (!errors.isEmpty()) {
      log.error("Not Valid Commande Fournisseur");
      throw new InvalidEntityException("Not Valid Commande Fournisseur", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID,
          errors);
    }
    Integer fournisseurId = dto.getFournisseur().getId();
    Optional<Fournisseur> fournisseur = fournisseurRepository.findById(fournisseurId);
    if (fournisseur.isEmpty()) {
      log.warn("Fournisseur with id ");
      throw new EntityNotFoundException("Not with the corresponding Id " + " was Found",
          ErrorCodes.FOURNISSEUR_NOT_FOUND);
    }
    var articlesErrors = new ArrayList<String>();
    if (ligneCommandeFournisseurs != null) {
      ligneCommandeFournisseurs.forEach((ligCmdFrs) -> {
        if (ligCmdFrs.getArticle() != null) {
          Optional<Article> article = articleRepository.findById(ligCmdFrs.getArticle().getId());
          if (article.isEmpty()) {
            articlesErrors.add("Article not found with id " + ligCmdFrs.getArticle().getId());

          } else {
            articlesErrors.add("Article not found with id " + ligCmdFrs.getArticle().getId());

          }
        }
      });
    }
    if (!articlesErrors.isEmpty()) {
      log.warn("");
      throw new InvalidEntityException("Article Not Found in DB", ErrorCodes.ARTICLE_NOT_FOUND, errors);
    }
    var savedCmdFrs = this.repository.save(DtoMapper.toEntity(dto, CommandeFournisseur.class));

    if (ligneCommandeFournisseurs != null) {
      ligneCommandeFournisseurs.forEach((ligCmdFr) -> {
        LigneCommandeFournisseur ligneCommandeFournisseur = DtoMapper.toEntity(ligCmdFr,
            LigneCommandeFournisseur.class);
        ligneCommandeFournisseur.setCommandeFournisseur(savedCmdFrs);
        ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);

      });

    }

    return DtoMapper.fromEntity(savedCmdFrs, CommandeFournisseurDto.class);
  }

}
