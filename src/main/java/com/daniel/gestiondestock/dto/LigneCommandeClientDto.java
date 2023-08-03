package com.daniel.gestiondestock.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LigneCommandeClientDto {
    private Integer id;
    private ArticleDto article;
    private CommandeClientDto commandeClient;
    private BigDecimal quantite;
    private BigDecimal prixunitaire;

    public LigneCommandeClientDto() {
    }

}
