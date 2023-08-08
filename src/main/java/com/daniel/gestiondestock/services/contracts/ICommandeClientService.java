package com.daniel.gestiondestock.services.contracts;

import com.daniel.gestiondestock.dto.CommandeClientDto;

public interface ICommandeClientService extends AbstractService<CommandeClientDto> {
  // CommandeClientDto findCommandeByDate(String date);
  CommandeClientDto findByCode(String code);
  CommandeClientDto findByIdEntreprise(Integer idEntreprise);

}
