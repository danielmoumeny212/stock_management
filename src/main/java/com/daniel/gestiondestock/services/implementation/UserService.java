package com.daniel.gestiondestock.services.implementation;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.daniel.gestiondestock.dto.ChangeUserPasswordDto;
import com.daniel.gestiondestock.dto.UserDto;
import com.daniel.gestiondestock.exception.EntityNotFoundException;
import com.daniel.gestiondestock.exception.ErrorCodes;
import com.daniel.gestiondestock.exception.InvalidEntityException;
import com.daniel.gestiondestock.exception.InvalidOperationException;
import com.daniel.gestiondestock.mapper.DtoMapper;
import com.daniel.gestiondestock.model.User;
import com.daniel.gestiondestock.repository.UserRepository;
import com.daniel.gestiondestock.services.contracts.IUtilisateurService;
import com.daniel.gestiondestock.validators.DtoValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements IUtilisateurService {
  private UserRepository repository;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDto changePassword(ChangeUserPasswordDto dto) {
    validate(dto);
    Optional<User> userOptional = this.repository.findById(dto.getId());
    if (userOptional.isEmpty()) {
      log.warn("none existing User  with id " + dto.getId());
      throw new EntityNotFoundException("none existing User  with id " + dto.getId(), ErrorCodes.UTILISATEUR_NOT_FOUND);

    }
    var user = userOptional.get();
    user.setPassword(this.passwordEncoder.encode(dto.getPassword()));

    return DtoMapper.fromEntity(user, UserDto.class);
  }

  @Override
  public UserDto findByEmail(String email) {
    return this.repository.findUserByEmail(email)
        .map((user) -> DtoMapper.fromEntity(user, UserDto.class))
        .orElseThrow(() -> new EntityNotFoundException("There's not user with email : " + email,
            ErrorCodes.UTILISATEUR_NOT_FOUND));
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("User id is null");
      return;
    }
    this.repository.deleteById(id);
  }

  @Override
  public List<UserDto> findAll() {
    return this.repository.findAll()
        .stream()
        .map((user) -> DtoMapper.fromEntity(user, UserDto.class))
        .collect(Collectors.toList());
  }

  @Override
  public UserDto findById(Integer id) {
    if (id == null) {
      log.error("User ID is null");
    }
    var foundedUser = this.repository.findById(id).map(
        user -> DtoMapper.toEntity(user, UserDto.class));

    return foundedUser.orElseThrow(() -> new EntityNotFoundException("There's no user with the given ID = " + id,
        ErrorCodes.UTILISATEUR_NOT_FOUND));
  }

  @Override
  public UserDto save(UserDto dto) {
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
        this.repository.save(DtoMapper.toEntity(dto, User.class)), UserDto.class);
  }

  private boolean userAlreadyExists(String email) {
    Optional<User> user = this.repository.findUserByEmail(email);
    return user.isPresent();
  }

  private void validate(ChangeUserPasswordDto dto) {
    validateDtoNotNull(dto);
    validateDtoIdNotNull(dto);
    validatePasswordsNotEmpty(dto);
    validateMatchingPasswords(dto);
  }

  private void logAndThrow(String logMessage, String errorMessage, ErrorCodes errorCode) {
    log.warn(logMessage);
    throw new InvalidOperationException(errorMessage, errorCode);
  }

  private void validateDtoNotNull(ChangeUserPasswordDto dto) {
    if (dto == null) {
      logAndThrow("Impossible de modifier le mot de passe avec un objet NULL",
          "Aucune information n'a ete fourni pour pouvoir changer le mot de passe",
          ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
    }
  }

  private void validateDtoIdNotNull(ChangeUserPasswordDto dto) {
    if (dto.getId() == null) {
      logAndThrow("Impossible de modifier le mot de passe avec un ID NULL",
          "ID utilisateur null:: Impossible de modifier le mote de passe",
          ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
    }
  }

  private void validatePasswordsNotEmpty(ChangeUserPasswordDto dto) {
    if (!StringUtils.hasLength(dto.getPassword()) || !StringUtils.hasLength(dto.getConfirmPassword())) {
      logAndThrow("Impossible de modifier le mot de passe avec un mot de passe NULL",
          "Mot de passe utilisateur null:: Impossible de modifier le mote de passe",
          ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
    }
  }

  private void validateMatchingPasswords(ChangeUserPasswordDto dto) {
    if (!dto.getPassword().equals(dto.getConfirmPassword())) {
      logAndThrow("Impossible de modifier le mot de passe avec deux mots de passe differents",
          "Mots de passe utilisateur non conformes:: Impossible de modifier le mote de passe",
          ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
    }
  }

}
