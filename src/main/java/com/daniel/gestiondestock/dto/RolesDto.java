package com.daniel.gestiondestock.dto;

import lombok.Data;

@Data
public class RolesDto {
    private Integer id;


    private String roleName;

    private UtilisateurDto utilisateur;

    RolesDto(){}

   
}
