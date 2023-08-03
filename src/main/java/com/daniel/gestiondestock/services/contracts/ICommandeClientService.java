package com.daniel.gestiondestock.services.contracts;

import com.daniel.gestiondestock.dto.CommandeClientDto;

public interface ICommandeClientService extends AbstractService<CommandeClientDto> {
  // CommandeClientDto findCommandeByDate(String date);

  CommandeClientDto findByIdEntreprise(String idEntreprise);

}
