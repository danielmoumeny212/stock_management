package com.daniel.gestiondestock.dto;
import com.daniel.gestiondestock.model.Client;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ClientDto {

    private Integer id;

    private String nom;
    private String prenom;
    private AdresseDto adresse;
    private String identreprise; 
    private String mail;

    private String numTel;

    private List<CommandeClientDto> commandeClients;


    public static ClientDto fromEntity(Client entity) {
        if(entity == null){
        return null;
        }
        var addresseDto = AdresseDto.fromEntity(entity.getAdresse());
        var commandeClientsDto = entity.getCommandeClients()
                .stream().map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());

        return ClientDto.builder()
                .id(entity.getId())
                .adresse(addresseDto)
                .commandeClients(commandeClientsDto)
                .nom(entity.getNom())
                .numTel(entity.getNumTel())
                .prenom(entity.getPrenom())
                .identreprise(entity.getIdEntreprise())
                .mail(entity.getMail())
                .build();
    }

     public static Client toEntity(ClientDto dto) {
        if(dto == null){
            return null;
        }
        var client = new Client();
        client.setId(dto.getId());
        client.setNom(dto.getNom());
        client.setPrenom(dto.getPrenom());
        client.setNumTel(dto.getNumTel());
        client.setIdEntreprise(dto.getIdentreprise());
        client.setAdresse(AdresseDto.toEntity(dto.getAdresse()));
         var commandeClientsDto = dto.getCommandeClients()
                .stream().map(CommandeClientDto::toEntity)
                .collect(Collectors.toList());
        client.setCommandeClients(commandeClientsDto);

//        client.setAdresse(dto.getAdresse());

        return client;


    }
}
