package com.daniel.gestiondestock.dto;
import lombok.Data;

import java.util.List;

@Data
public class UtilisateurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private String motDePasse;

    private AdresseDto adresse;

    private String photo;


    private String dateDeNaissance;

    private EntrepriseDto entreprise;

    private List<RolesDto> roles;


   UtilisateurDto(){}

 
}


