package com.daniel.gestiondestock.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="lignevente")
public class LigneVente extends  AbstractEntity{
    @ManyToOne
    @JoinColumn(name="idvente")
    private Ventes vente;

    @Column(name="quantite")
    private BigDecimal quantite;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;
  

    @Column(name="identreprise")
    private Integer idEntreprise;
    
    @Column(name="prixunitaire")
    private BigDecimal prixUnitaire;


}
