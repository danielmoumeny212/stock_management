package com.daniel.gestiondestock.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="client")
public class Client  extends AbstractEntity{

    @Column(name="nom")
    private String nom;
    @Column(name="prenom")
    private String prenom;
    @Embedded
    private Adresse adresse;
    
    @Column(name="identreprise")
    private String idEntreprise;
    
    @Column(name="numTel")
    private String numTel;

    @Column(name="mail")
    private String mail;

    @OneToMany(mappedBy = "client")
    private List<CommandeClient> commandeClients;
}
