package com.daniel.gestiondestock.dto;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class VentesDto {

    private Integer id;

    private String code;

    private Instant dateVente;

    private String commentaire;

    private List<LigneVenteDto> ligneVentes;

    private Integer idEntreprise;

    public VentesDto() {

    }

    // public static VentesDto fromEntity(Ventes instance){
    // if(instance == null){
    // return null;
    // }
    // return VentesDto.builder()
    // .id(instance.getId())
    // .code(instance.getCode())
    // .dateVente(instance.getDateVente())
    // .commentaire(instance.getCommentaire())
    // .build();
    // }

    // public static Ventes toEntity(VentesDto dto){
    // if(dto == null){
    // return null;
    // }
    // var instance = new Ventes();
    // instance.setId(dto.getId());
    // instance.setDateVente(dto.getDateVente());
    // instance.setCommentaire(dto.getCommentaire());
    // instance.setCode(dto.getCode());
    // return instance ;
    // }
}
