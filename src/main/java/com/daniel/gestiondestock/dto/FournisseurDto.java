package com.daniel.gestiondestock.dto;


import lombok.Data;

import java.util.List;

@Data
public class FournisseurDto {
    private Integer id;

    private String nom;

    private String prenom;

    private String photo;

    private AdresseDto adresse;


    private String mail;

    private String numTel;

    private List<CommandeFournisseurDto> commandeFournisseurs;

    public FournisseurDto() {
    }

}
