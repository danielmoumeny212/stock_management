package com.daniel.gestiondestock.model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="articles")
public class Article extends AbstractEntity {
     @Column(name="codearticle")
     private String designation;

     @Column(name="code")
     private String codeArticle;

     @Column(name="prixunitaireht")
     private BigDecimal prixunitaireHt;

     @Column(name="tauxtva")
     private BigDecimal tauxTva;

     @Column(name="prixunitaireTtc")
     private BigDecimal prixUnitaireTtc;
     
     @Column(name="identreprise")
     private Integer idEntreprise;

     @ManyToOne // 1 a plusieurs pour indiquer
     @JoinColumn(name= "idcategory")
     private Category category;


     private String photo;
}
