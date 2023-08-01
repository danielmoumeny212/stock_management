package com.daniel.gestiondestock.validators;
import com.daniel.gestiondestock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> validate (UtilisateurDto dto){
        List<String> errors = new ArrayList<>();

        if(dto == null){
            errors.add("Veuillez renseigner le 'Nom'de l'utilisateur");
            errors.add("Veuillez renseigner le 'Prenom' de l'utilisateur");
            errors.add("Veuillez renseigner le 'Mot de Passe' de l'utilisateur");
            errors.add("le champs de 'Adresse' est obligatoire");
            return  errors;
        }
        if(dto == null || !StringUtils.hasLength(dto.getNom())){
            errors.add("Veuillez renseigner le nom de l'utilisateur");
        }
        if(!StringUtils.hasLength(dto.getPrenom())){
            errors.add("Veuillez renseigner le prenom de l'utilisateur");
        }
        if(!StringUtils.hasLength(dto.getMotDePasse())){
            errors.add("Veuillez renseigner le mot de passe de l'utilisateur");
        }
        if(!StringUtils.hasLength(dto.getEmail())){
            errors.add("Veuillez renseigner 'Email' de l'utilisateur");
        }
        if(!StringUtils.hasLength(dto.getMotDePasse())){
            errors.add("Veuillez renseigner le mot de passe de l'utilisateur");
        }

        if(dto.getDateDeNaissance() == null ){
            errors.add("le champs de 'Date de Naissance ' est obligatoire");
        }
        if(dto.getAdresse() == null ){
            errors.add("le champs de l'adresse est obligatoire");
        } else {
            if(!StringUtils.hasLength(dto.getAdresse().getAdresse1())){
                errors.add("le champ 'Adresse 1' est obligatoire");
            }
            if(!StringUtils.hasLength(dto.getAdresse().getVille())){
                errors.add("le champ 'Ville' est obligatoire");
            }
            if(!StringUtils.hasLength(dto.getAdresse().getCodePostale())){
                errors.add("le champ 'Adresse 1' est obligatoire");
            }
            if(!StringUtils.hasLength(dto.getAdresse().getPays())){
                errors.add("le champ 'Pays' est obligatoire");
            }
        }

        return errors;
    }
}
