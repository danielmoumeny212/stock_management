package com.daniel.gestiondestock.dto;


import lombok.Data;
import java.math.BigDecimal;

@Data
public class LigneVenteDto {

    private Integer id;

    private VentesDto vente;
  
    private ArticleDto article;
  
    private BigDecimal quantite;
  
    private BigDecimal prixUnitaire;
  
    private Integer idEntreprise;

    public LigneVenteDto(){
        

    }

    
}
