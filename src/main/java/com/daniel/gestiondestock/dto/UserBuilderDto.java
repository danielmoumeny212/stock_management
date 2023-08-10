package com.daniel.gestiondestock.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserBuilderDto {

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
  
}
