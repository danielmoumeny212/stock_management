package com.daniel.gestiondestock.dto;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class CommandeClientDto {
    private Integer id;
    private String code;
    private Instant dateCommande;
    private ClientDto client;
    private List<LigneCommandeClientDto> ligneCommandeClient;
    public CommandeClientDto() {
    }


}
