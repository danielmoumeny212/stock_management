package com.daniel.gestiondestock.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@Data
@Embeddable
public class Adresse {
    @Column(name="adresse1")
    private String adresse1;

    @Column(name="adresse2")

    private String adresse2;

    @Column(name="ville")
    private String ville;

    @Column(name="codepostale")
    private String codePostale;
    

    @Column(name="pays")
    private String pays;

}
