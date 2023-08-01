package com.daniel.gestiondestock.validators;
import com.daniel.gestiondestock.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {

    public static List<String> validate (ArticleDto dto) {
        List<String> errors = new ArrayList<>();
        if(dto == null){
            errors.add("Veuillez renseigner 'Code de l'Article'  de l'article ");
            errors.add("Veuillez renseigner la designation  de l'article");
            errors.add("Veuillez renseigner le prix unitaire HT   de l'article");
            errors.add("Veuillez renseigner le taux tva de l'article");
            errors.add("Veuillez renseigner le prix TTc   de l'article");
            errors.add("Veuillez selectionner une categories");
            return errors;


        }
        if(!StringUtils.hasLength(dto.getCodeArticle())){
            errors.add("Veuillez renseigner 'Code de l'Article'  de l'article ");
        }
        if(!StringUtils.hasLength(dto.getCodeArticle())){
            errors.add("Veuillez renseigner la designation  de l'article");
        }
        if(dto.getPrixunitaireHt() == null){
            errors.add("Veuillez renseigner le prix unitaire HT   de l'article");
        }
        if(dto.getTauxTva() == null){
            errors.add("Veuillez renseigner le taux tva de l'article");
        }
        if(dto.getPrixUnitaireTtc() == null){
            errors.add("Veuillez renseigner le prix TTc   de l'article");
        }

        if(dto.getCategory() == null ){
            errors.add("Veuillez selectionner une categorie");
        }

        return errors;
    }
}
