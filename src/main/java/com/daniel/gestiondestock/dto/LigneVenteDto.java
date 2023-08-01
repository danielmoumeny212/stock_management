package com.daniel.gestiondestock.dto;


import com.daniel.gestiondestock.model.LigneVente;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder

public class LigneVenteDto {

    private Integer id;

    private VentesDto vente;
    private BigDecimal quantite;
    private BigDecimal prixUnitaire;


    public static LigneVenteDto fromEntity(LigneVente instance){
     return LigneVenteDto.builder()
             .id(instance.getId())
             .vente(VentesDto.fromEntity(instance.getVente()))
             .quantite(instance.getQuantite())
             .prixUnitaire(instance.getPrixUnitaire())
             .build();
    }

    public static LigneVente toEntity(LigneVenteDto dto){
         var instance = new LigneVente();
         instance.setVente(VentesDto.toEntity(dto.getVente()));
         instance.setId(dto.getId());
         instance.setQuantite(dto.getQuantite());
         instance.setPrixUnitaire(dto.getPrixUnitaire());

         return instance;
    }
}
