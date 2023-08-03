package com.daniel.gestiondestock.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LigneCommandeFournisseurDto {

    private Integer id;

    private ArticleDto article ;

    private CommandeFournisseurDto commandeFournisseur;

    private BigDecimal prixUnitaire;

    private BigDecimal quantite;

    public LigneCommandeFournisseurDto() {
    }
}
