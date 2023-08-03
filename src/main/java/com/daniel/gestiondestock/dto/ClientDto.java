package com.daniel.gestiondestock.dto;

import lombok.Data;

import java.util.List;


@Data

public class ClientDto {

    private Integer id;

    private String nom;
    private String prenom;
    private AdresseDto adresse;
    private String identreprise; 
    private String mail;
    private String numTel;

    private List<CommandeClientDto> commandeClients;

    public ClientDto() {
    }

}
