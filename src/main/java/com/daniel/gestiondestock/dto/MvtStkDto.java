package com.daniel.gestiondestock.dto;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvtStkDto {

    private Integer id;

    private ArticleDto article;

    private BigDecimal quantite;
    private Instant dateMvt;

    private TypeMvtStkDto  typeMvt;
}
