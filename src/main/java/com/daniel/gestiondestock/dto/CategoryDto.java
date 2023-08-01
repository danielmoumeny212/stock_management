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

        @JsonIgnore
        private List<ArticleDto> articles;

       static public CategoryDto fromEntity(Category category){
                if (category == null){
                        return null; 
                }
                return CategoryDto.builder()
                        .id(category.getId())
                        .code(category.getCode())
                        .designation(category.getDesignation())
                        .build();
        }

        static public Category toEntity (CategoryDto categoryDto){
                if(categoryDto == null){
                        return null; 

                }
               var category = new Category();
                category.setId(categoryDto.getId());
                category.setCode(categoryDto.getCode());
                category.setDesignation(categoryDto.getDesignation());

                return category;
        }
}
