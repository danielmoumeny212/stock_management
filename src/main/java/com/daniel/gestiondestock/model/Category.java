package com.daniel.gestiondestock.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="category")
public class Category extends AbstractEntity{

    @Column(name="code")
    private String code;

    @Column(name="identreprise")
    private Integer idEntreprise;


    @Column(name="designation")
    private String designation;

    @OneToMany(mappedBy = "category") // relation 1 à plusieurs
    private List<Article> articles;

}
