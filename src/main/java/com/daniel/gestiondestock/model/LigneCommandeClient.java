package com.daniel.gestiondestock.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="lignecommandeclient")
public class LigneCommandeClient  extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name="idarticle")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "idcommandeclient")
    private CommandeClient commandeClient;

    @Column(name="quantite")
    private BigDecimal quantite;
    
    @Column(name="identreprise")
    private String idEntreprise;
    
    @Column(name="prixunitaire")
    private BigDecimal prixunitaire;

}
