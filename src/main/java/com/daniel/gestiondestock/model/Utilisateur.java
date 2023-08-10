package com.daniel.gestiondestock.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;


@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="users")
public class Utilisateur  extends  AbstractEntity{
    @Column(name="nom")
    private String nom;

    @Column(name="prenom")
    private String prenom;

    @Column(name="email")
     private String email;

    @Column(name="motdepasse")
    private String motDePasse;

    @Embedded
    private Adresse adresse;

    @Column(name="photo")
    private String photo;


    @Column(name="datedenaissance")
    private Instant dateDeNaissance;

    @ManyToOne
    @JoinColumn(name="identreprise")
    private Entreprise entreprise;

    @OneToMany(mappedBy ="utilisateur")
    private List<Roles>roles;

}
