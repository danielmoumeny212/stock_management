package com.daniel.gestiondestock.services.implementation;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.gestiondestock.mapper.DtoMapper;
import com.daniel.gestiondestock.model.Entreprise;
import com.daniel.gestiondestock.model.Roles;
import com.daniel.gestiondestock.dto.EntrepriseDto;
import com.daniel.gestiondestock.dto.RolesDto;
import com.daniel.gestiondestock.dto.UserBuilderDto;
import com.daniel.gestiondestock.dto.UserDto;
import com.daniel.gestiondestock.exception.EntityNotFoundException;
import com.daniel.gestiondestock.exception.ErrorCodes;
import com.daniel.gestiondestock.exception.InvalidEntityException;
import com.daniel.gestiondestock.repository.EntrepriseRepository;
import com.daniel.gestiondestock.repository.RolesRepository;
import com.daniel.gestiondestock.services.contracts.IEntrepriseService;
import com.daniel.gestiondestock.validators.DtoValidator;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Transactional(rollbackOn = Exception.class)
@Service
@Slf4j
public class EntrepriseService implements IEntrepriseService {
  private EntrepriseRepository repository;
  private UserService utilisateurService;
  private RolesRepository rolesRepository;

  @Autowired
  public EntrepriseService(EntrepriseRepository repository, UserService utilisateurService,
      RolesRepository rolesRepository) {
    this.repository = repository;
    this.utilisateurService = utilisateurService;
    this.rolesRepository = rolesRepository;
  }

  @Override
  public EntrepriseDto save(EntrepriseDto dto) {
    var errors = DtoValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Entreprise is not valid {}", dto);
      throw new InvalidEntityException("Entreprise not Valid", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
    }
    EntrepriseDto savedEntreprise = DtoMapper.fromEntity(
        this.repository.save(DtoMapper.toEntity(dto, Entreprise.class)), EntrepriseDto.class);

    UserDto utilisateur = fromEntreprise(savedEntreprise);

    var savedUser = this.utilisateurService.save(utilisateur);

    RolesDto rolesDto = RolesDto.builder()
        .roleName("ADMIN")
        .utilisateur(savedUser)
        .build();
    rolesRepository.save(DtoMapper.fromEntity(rolesDto, Roles.class));

    return savedEntreprise;
  }

  private UserDto fromEntreprise(EntrepriseDto dto) {
    var user = UserBuilderDto.builder()
        .adresse(dto.getAdresse())
        .nom(dto.getNom())
        .prenom(dto.getCodeFiscal())
        .email(dto.getEmail())
        .motDePasse(generateRandomPassword())
        .entreprise(dto)
        .dateDeNaissance(Instant.now())
        .photo(dto.getPhoto())
        .build();

    return DtoMapper.fromEntity(user, UserDto.class);

  }

  private String generateRandomPassword() {
    return "som3R@nd0mP@$$word";
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
