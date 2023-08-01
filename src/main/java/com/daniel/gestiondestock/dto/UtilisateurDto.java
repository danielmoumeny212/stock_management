package com.daniel.gestiondestock.dto;
import com.daniel.gestiondestock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
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


    public static UtilisateurDto  fromEntity(Utilisateur instance){
        if (instance == null) {
            return null;
        }
        var rolesDto = instance.getRoles()
                .stream()
                .map(RolesDto::fromEntity)
                .collect(Collectors.toList());

        return UtilisateurDto.builder()
                .id(instance.getId())
                .adresse(AdresseDto.fromEntity(instance.getAdresse()))
                .nom(instance.getNom())
                .dateDeNaissance(instance.getDateDeNaissance())
                .photo(instance.getPhoto())
                .email(instance.getEmail())
                .entreprise(EntrepriseDto.fromEntity(instance.getEntreprise()))
                .roles(rolesDto)
                .build();
    }

    public static Utilisateur toEntity(UtilisateurDto dto){
        if (dto == null) {
            return null;
        }
        var utilisateur = new Utilisateur();
        var roles = dto.getRoles()
                        .stream().map(RolesDto::toEntity).collect(Collectors.toList());
         utilisateur.setAdresse(AdresseDto.toEntity(dto.getAdresse()));
         utilisateur.setPhoto(dto.getPhoto());
         utilisateur.setId(dto.getId());
         utilisateur.setNom(dto.getNom());
         utilisateur.setPrenom(dto.getPrenom());
         utilisateur.setEmail(dto.getEmail());
         utilisateur.setRoles(roles);

        return utilisateur;
    }
}


