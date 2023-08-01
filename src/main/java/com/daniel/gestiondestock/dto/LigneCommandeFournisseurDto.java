package com.daniel.gestiondestock.dto;




import com.daniel.gestiondestock.model.LigneCommandeFournisseur;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeFournisseurDto {

    private Integer id;

    private ArticleDto article ;

    private CommandeFournisseurDto commandeFournisseur;

    private BigDecimal prixUnitaire;

    private BigDecimal quantite;

    public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur instance){
        return LigneCommandeFournisseurDto.builder()
                .id(instance.getId())
                .article(ArticleDto.fromEntity(instance.getArticle()))
                .prixUnitaire(instance.getPrixUnitaire())
                .quantite(instance.getQuantite())
                .commandeFournisseur(CommandeFournisseurDto.fromEntity(instance.getCommandeFournisseur()))
                .build();
    }

    public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto dto){
          var instance = new LigneCommandeFournisseur();
          instance.setCommandeFournisseur(CommandeFournisseurDto.toEntity(dto.getCommandeFournisseur()));
          instance.setId(dto.getId());
          instance.setArticle(ArticleDto.toEntity(dto.getArticle()));
          instance.setQuantite(dto.getQuantite());
          instance.setPrixUnitaire(dto.getPrixUnitaire());
          return instance;
    }
}
