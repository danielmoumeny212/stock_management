package com.daniel.gestiondestock.dto;

import com.daniel.gestiondestock.model.LigneCommandeClient;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeClientDto {
    private Integer id;
    private ArticleDto article;
    private CommandeClientDto commandeClient;
    private BigDecimal quantite;
    private BigDecimal prixunitaire;

    public static LigneCommandeClientDto fromEntity(LigneCommandeClient instance) {
        if (instance == null) {
            return null;
        }
        return LigneCommandeClientDto.builder()
                .id(instance.getId())
                .article(ArticleDto.fromEntity(instance.getArticle()))
                .commandeClient(CommandeClientDto.fromEntity(instance.getCommandeClient()))
                .quantite(instance.getQuantite())
                .prixunitaire(instance.getPrixunitaire())
                .build();
    }

    public static LigneCommandeClient toEntity(LigneCommandeClientDto dto) {
        if (dto == null) {
            return null;
        }
        var ligneCommandeClient = new LigneCommandeClient();
                ligneCommandeClient.setId(dto.getId());
                ligneCommandeClient.setArticle(ArticleDto.toEntity(dto.getArticle()));
                ligneCommandeClient.setCommandeClient(CommandeClientDto.toEntity(dto.getCommandeClient()));
                ligneCommandeClient.setQuantite(dto.getQuantite());
                ligneCommandeClient.setPrixunitaire(dto.getPrixunitaire());
                return  ligneCommandeClient;
    }
}
