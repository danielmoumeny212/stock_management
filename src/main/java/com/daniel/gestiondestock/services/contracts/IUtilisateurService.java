package com.daniel.gestiondestock.services.contracts;



import com.daniel.gestiondestock.dto.ChangeUserPasswordDto;
import com.daniel.gestiondestock.dto.UtilisateurDto;

public interface IUtilisateurService extends AbstractService<UtilisateurDto> {
 
  UtilisateurDto findByEmail(String email);
  UtilisateurDto changePassword(ChangeUserPasswordDto dto);
}
