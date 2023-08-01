package com.daniel.gestiondestock.dto;


import com.daniel.gestiondestock.model.Fournisseur;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class FournisseurDto {
    private Integer id;

    private String nom;

    private String prenom;

    private String photo;

    private AdresseDto adresse;


    private String mail;

    private String numTel;

    private List<CommandeFournisseurDto> commandeFournisseurs;


    public static FournisseurDto fromEntity (Fournisseur instance){
        var commandeFournisseurDto = instance.getCommandeFournisseurs()
                .stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());

        return  FournisseurDto.builder()
                .id(instance.getId())
                .adresse(AdresseDto.fromEntity(instance.getAdresse()))
                .nom(instance.getNom())
                .mail(instance.getMail())
                .prenom(instance.getPrenom())
                .numTel(instance.getNumTel())
                .commandeFournisseurs(commandeFournisseurDto)
                .photo(instance.getPhoto())
                .build();

    }

    public static Fournisseur toEntity (FournisseurDto dto){

        var instance = new Fournisseur();
        var commandeFournisseur = dto.getCommandeFournisseurs()
                .stream()
                .map(CommandeFournisseurDto::toEntity)
                .collect(Collectors.toList());
        instance.setId(dto.getId());
        instance.setNom(dto.getNom());
        instance.setMail(dto.getMail());
        instance.setAdresse(AdresseDto.toEntity(dto.getAdresse()));
        instance.setPhoto(dto.getPhoto());
        instance.setPrenom(dto.getPrenom());
        instance.setNumTel(dto.getNumTel());
        instance.setCommandeFournisseurs(commandeFournisseur);
        return instance;
    }
}
