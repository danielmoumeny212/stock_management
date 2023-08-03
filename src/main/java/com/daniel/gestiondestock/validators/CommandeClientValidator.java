package com.daniel.gestiondestock.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.daniel.gestiondestock.dto.CommandeClientDto;

public class CommandeClientValidator {
  public static List<String> validate(CommandeClientDto dto) {
    List<String> errors = new ArrayList<>();
    if (dto == null) {
      errors.add("Code is required");
      errors.add("DateCommande is required");
      errors.add("IdEntreprise is required");
      errors.add("Client is required");
      errors.add("LigneCommandeClient is required");
      return errors;
    }
    if (!StringUtils.hasLength(dto.getCode())) {
      errors.add("Code is required");
    }
    if (dto.getDateCommande() == null) {
      errors.add("DateCommande is required");
    }
    if (dto.getClient() == null) {
      errors.add("Client is required");
    }
    if (dto.getLigneCommandeClient() == null) {
      errors.add("LigneCommandeClient is required");

    }

    return errors;
  }
}
