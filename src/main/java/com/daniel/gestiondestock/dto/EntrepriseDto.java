package com.daniel.gestiondestock.dto;


import lombok.Data;

import java.util.List;

@Data
public class EntrepriseDto {
    private Integer id;


    private String nom;

    private String description;

    private String codeFiscal;

    private String photo;

    private String email;

    private String numTel;

    private String siteweb;

    private AdresseDto adresse;


    private List<UtilisateurDto> utilisateurs;

    EntrepriseDto(){}

   
}
