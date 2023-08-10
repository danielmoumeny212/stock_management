package com.daniel.gestiondestock.services.implementation;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.daniel.gestiondestock.dto.ChangeUserPasswordDto;
import com.daniel.gestiondestock.dto.UtilisateurDto;
import com.daniel.gestiondestock.exception.ErrorCodes;
import com.daniel.gestiondestock.exception.InvalidEntityException;
import com.daniel.gestiondestock.mapper.DtoMapper;
import com.daniel.gestiondestock.model.Utilisateur;
import com.daniel.gestiondestock.repository.UtilisateurRepository;
import com.daniel.gestiondestock.services.contracts.IUtilisateurService;
import com.daniel.gestiondestock.validators.DtoValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtilisateurService implements IUtilisateurService {
  private UtilisateurRepository repository;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public UtilisateurService(UtilisateurRepository repository, PasswordEncoder passwordEncoder) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UtilisateurDto changePassword(ChangeUserPasswordDto dto) {
    return null;
  }

  @Override
  public UtilisateurDto findByEmail(String email) {
    return null;
  }

  @Override
  public void delete(Integer id) {

  }

  @Override
  public List<UtilisateurDto> findAll() {
    return null;
  }

  @Override
  public UtilisateurDto findById(Integer id) {
    return null;
  }

  @Override
  public UtilisateurDto save(UtilisateurDto dto) {
    var errors = DtoValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("User is not valid {}", dto);
      throw new InvalidEntityException("User is not valid", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
    }
    if (this.userAlreadyExists(dto.getEmail())) {
      throw new InvalidEntityException("There is already a user with the given email",
          ErrorCodes.UTILISATEUR_ALREADY_EXISTS,
          Collections.singletonList("There is already a user with the given email"));
    }
    dto.setMotDePasse(passwordEncoder.encode(dto.getMotDePasse()));
    return DtoMapper.fromEntity(
        this.repository.save(DtoMapper.toEntity(dto, Utilisateur.class)), UtilisateurDto.class);
  }

  private boolean userAlreadyExists(String email) {
    Optional<Utilisateur> user = this.repository.findUtilisateurByEmail(email);
    return user.isPresent();
  }

}
