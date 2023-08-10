package com.daniel.gestiondestock.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;


@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "mouvementdestock")
public class MvtStk extends  AbstractEntity{

    @ManyToOne
    @JoinColumn(name="idarticle")
    private Article article;

    @Column(name="quantite")
    private BigDecimal quantite;
    
    @Column(name="datemvt")
    private Instant dateMvt;

    @Column(name="typemvt")
    private TypeMvtStk typeMvt;

    @Column(name="identreprise")
    private Integer idEntreprise;
    
}
