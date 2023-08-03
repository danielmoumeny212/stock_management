package com.daniel.gestiondestock.validators;

import com.daniel.gestiondestock.dto.CommandeFournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeFournisseur {
    public static List<String> validate (CommandeFournisseurDto dto) {
        List<String> errors = new ArrayList<>();
        if(dto == null){
            errors.add("Veuillez renseigner le code du fournisseur");
            errors.add("Veuillez renseigner la date de commande");
            errors.add("Veuillez renseigner le fournisseur");
            errors.add("Veuillez renseigner la ligne de commande fournisseur");
        }
        if(!StringUtils.hasLength(dto.getCode())){
            errors.add("Veuillez renseigner le code du fournisseur");
        }



        return errors;
    }

}
