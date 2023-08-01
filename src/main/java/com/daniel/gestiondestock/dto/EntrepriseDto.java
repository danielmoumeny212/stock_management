package com.daniel.gestiondestock.dto;

import com.daniel.gestiondestock.model.Entreprise;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class EntrepriseDto {
    private Integer id;


    private String nom;

    private String description;

    private String codeFiscal;

    private String photo;

    private String email;

    private String numTel;

    private String siteweb;

    private List<UtilisateurDto> utilisateurs;

    public static EntrepriseDto fromEntity(Entreprise instance){
        var utilisateurs = instance.getUtilisateurs()
                .stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());

      return EntrepriseDto.builder()
              .id(instance.getId())
              .nom(instance.getNom())
              .siteweb(instance.getSiteweb())
              .description(instance.getDescription())
              .codeFiscal(instance.getCodeFiscal())
              .photo(instance.getPhoto())
              .utilisateurs(utilisateurs)
              .email(instance.getEmail())
              .numTel(instance.getNumTel())
              .build();
    }


    public static Entreprise toEntity(EntrepriseDto dto){
        if (dto == null) {
            return null;
        }
        var entreprise  = new Entreprise();
        var utilisateurs = dto.getUtilisateurs()
                        .stream().map(UtilisateurDto::toEntity).collect(Collectors.toList());
        entreprise.setId(dto.getId());
        entreprise.setPhoto(dto.getPhoto());
        entreprise.setEmail(dto.getEmail());
        entreprise.setDescription(dto.getDescription());
        entreprise.setNumTel(dto.getNumTel());
        entreprise.setSiteweb(dto.getSiteweb());
        entreprise.setNom(dto.getNom());
        entreprise.setUtilisateurs(utilisateurs);
        entreprise.setCodeFiscal(dto.getCodeFiscal());
        return entreprise;
    }
}
