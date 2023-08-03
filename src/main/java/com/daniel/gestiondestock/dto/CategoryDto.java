package com.daniel.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;


@Data
// @Builder
public class CategoryDto {
        private Integer id;

        private String code;

        private String designation;

        private String identreprise;

        @JsonIgnore
        private List<ArticleDto> articles;

        public CategoryDto() {
     
            }
}
