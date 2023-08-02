package com.daniel.gestiondestock.dto;

import com.daniel.gestiondestock.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class CategoryDto {
        private Integer id;

        private String code;

        private String designation;

        private String identreprise;

        @JsonIgnore
        private List<ArticleDto> articles;

       static public CategoryDto fromEntity(Category instance){
                if (instance == null){
                        return null; 
                }
                return CategoryDto.builder()
                        .id(instance.getId())
                        .code(instance.getCode())
                        .designation(instance.getDesignation())
                        .identreprise(instance.getIdEntreprise())
                        .build();
        }

        static public Category toEntity (CategoryDto dto){
                if(dto == null){
                        return null; 

                }
               var category = new Category();
                category.setId(dto.getId());
                category.setCode(dto.getCode());
                category.setDesignation(dto.getDesignation());
                category.setIdEntreprise(dto.getIdentreprise());

                return category;
        }
}
