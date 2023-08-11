package com.daniel.gestiondestock.controllers.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.daniel.gestiondestock.dto.CommandeFournisseurDto;

public interface CommandeFournisseurApi extends AbstractApi<CommandeFournisseurDto> {
  @GetMapping(value = "code/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
  CommandeFournisseurDto findByCode(@PathVariable(name = "code") String code);
}
