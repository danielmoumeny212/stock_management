package com.daniel.gestiondestock.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ArticleDto {
    private Integer id;

    private String designation;

    private String codeArticle;

    private BigDecimal prixunitaireHt;

    private BigDecimal tauxTva;

    private String identreprise; 

    private BigDecimal prixUnitaireTtc;

    private CategoryDto category;


    private String photo;

    public ArticleDto() {
        // Constructeur sans argument (constructeur par défaut)
    }

}