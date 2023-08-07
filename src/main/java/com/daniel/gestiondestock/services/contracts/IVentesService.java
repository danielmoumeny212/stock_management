package com.daniel.gestiondestock.services.contracts;

import com.daniel.gestiondestock.dto.VentesDto;

public interface IVentesService  extends AbstractService<VentesDto>{
   VentesDto findByCode(String code);
}
