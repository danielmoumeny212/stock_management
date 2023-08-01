package com.daniel.gestiondestock.dto;

import com.daniel.gestiondestock.model.CommandeFournisseur;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CommandeFournisseurDto {
    private Integer id;


    private String code;

    private Instant dateCommande;

    private FournisseurDto fournisseur;

    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur) {
        var ligneCommandeFournisseurDto = commandeFournisseur.getLigneCommandeFournisseurs()
                .stream()
                .map(LigneCommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());

        return CommandeFournisseurDto.builder()
                .id(commandeFournisseur.getId())
                .code(commandeFournisseur.getCode())
                .dateCommande(commandeFournisseur.getDateCommande())
                .fournisseur(FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))
                .ligneCommandeFournisseurs(ligneCommandeFournisseurDto)
                .build();
    }

    public static CommandeFournisseur toEntity(CommandeFournisseurDto dto) {
              var instance = new CommandeFournisseur();
               var ligneCommandeFournisseur = dto.getLigneCommandeFournisseurs()
                               .stream().map(LigneCommandeFournisseurDto::toEntity)
                               .collect(Collectors.toList());
                instance.setId(dto.getId());
                instance.setCode(dto.getCode());
                instance.setDateCommande(dto.getDateCommande());
                instance.setFournisseur(FournisseurDto.toEntity(dto.getFournisseur()));
                instance.setLigneCommandeFournisseurs(ligneCommandeFournisseur);
                return instance;
    }

}
