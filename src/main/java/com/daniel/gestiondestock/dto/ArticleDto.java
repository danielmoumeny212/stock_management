package com.daniel.gestiondestock.dto;


import com.daniel.gestiondestock.model.Article;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ArticleDto  {
    private Integer id;

    private String designation;

    private String codeArticle;

    private BigDecimal prixunitaireHt;

    private BigDecimal tauxTva;

    private BigDecimal prixUnitaireTtc;

    private CategoryDto category;


    private String photo;


     public  static ArticleDto fromEntity(Article instance){
        if (instance == null){
            return null;
        }
        var categoryDto = CategoryDto.fromEntity(instance.getCategory());

        return ArticleDto.builder()
                .id(instance.getId())
                .category(categoryDto)
                .designation(instance.getDesignation())
                .prixunitaireHt(instance.getPrixunitaireHt())
                .codeArticle(instance.getCodeArticle())
                .photo(instance.getPhoto())
                .tauxTva(instance.getTauxTva())
                .build();

    }

    public static Article toEntity(ArticleDto dto){
         var article = new Article();
         var category = CategoryDto.toEntity(dto.getCategory());
          article.setId(dto.getId());
          article.setCategory(category);
          article.setDesignation(dto.getDesignation());
          article.setCodeArticle(dto.getCodeArticle());
          article.setPhoto(dto.getPhoto());
          article.setPrixunitaireHt(dto.getPrixunitaireHt());
          article.setTauxTva(dto.getTauxTva());

         return article;


    }
}