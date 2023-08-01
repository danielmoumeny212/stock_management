package com.daniel.gestiondestock.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="commandefournisseur")
public class CommandeFournisseur extends AbstractEntity {

    @Column(name="code")
    private String code;

    @Column(name="datecommande")
    private Instant dateCommande;

    @ManyToOne
    @JoinColumn(name="idfournisseur")
    private Fournisseur fournisseur;

    @Column(name="identreprise")
    private String idEntreprise;

    @OneToMany(mappedBy = "commandeFournisseur")
    private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;
}
