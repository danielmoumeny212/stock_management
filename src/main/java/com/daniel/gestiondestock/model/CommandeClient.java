package com.daniel.gestiondestock.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "commandeclient")
public class CommandeClient extends AbstractEntity {

    @Column(name = "code")
    private String code;

    @Column(name = "datecommande")
    private Instant dateCommande;

    @ManyToOne
    @JoinColumn
    private Client client;

    @Column(name = "identreprise")
    private Integer idEntreprise;
    

    @ManyToMany(mappedBy = "commandeClient")
    private List<LigneCommandeClient> ligneCommandeClient;
}
