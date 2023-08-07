package com.daniel.gestiondestock.services.contracts;

import com.daniel.gestiondestock.dto.CommandeFournisseurDto;

public interface ICommandeFournisseurService extends AbstractService<CommandeFournisseurDto> {
  CommandeFournisseurDto findByCode(String code);

}