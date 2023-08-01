package com.daniel.gestiondestock.dto;

import com.daniel.gestiondestock.model.CommandeClient;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CommandeClientDto {
    private Integer id;
    private String code;
    private Instant dateCommande;
    private ClientDto client;
    private List<LigneCommandeClientDto> ligneCommandeClient;

    public static CommandeClientDto fromEntity(CommandeClient commandeClient) {
        ClientDto clientDto = ClientDto.fromEntity(commandeClient.getClient());
        List<LigneCommandeClientDto> ligneCommandeClientDtoList = commandeClient.getLigneCommandeClient()
                .stream()
                .map(LigneCommandeClientDto::fromEntity)
                .collect(Collectors.toList());

        return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                .client(clientDto)
                .ligneCommandeClient(ligneCommandeClientDtoList)
                .build();
    }

    public static CommandeClient toEntity(CommandeClientDto dto){
        if (dto == null){
            return null;
        }
        var client = ClientDto.toEntity(dto.getClient());
        var ligneCommandeClient =  dto.getLigneCommandeClient()
                .stream().map(LigneCommandeClientDto::toEntity)
                .collect(Collectors.toList());

        var commandeClient = new CommandeClient();
        commandeClient.setClient(client);
        commandeClient.setDateCommande(dto.getDateCommande());
        commandeClient.setId(dto.getId());
        commandeClient.setCode(dto.getCode());
        commandeClient.setLigneCommandeClient(ligneCommandeClient);

        return commandeClient;
    }
}
