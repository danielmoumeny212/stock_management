package com.daniel.gestiondestock.services.contracts;

import com.daniel.gestiondestock.dto.ChangeUserPasswordDto;
import com.daniel.gestiondestock.dto.UserDto;

public interface IUtilisateurService extends AbstractService<UserDto> {

  UserDto findByEmail(String email);

  UserDto changePassword(ChangeUserPasswordDto dto);
}
