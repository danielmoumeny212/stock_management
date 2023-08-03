package com.daniel.gestiondestock.dto;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class CommandeFournisseurDto {
    private Integer id;


    private String code;

    private Instant dateCommande;

    private FournisseurDto fournisseur;

    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

    public CommandeFournisseurDto() {
    }

  

}
