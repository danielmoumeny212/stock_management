package com.daniel.gestiondestock.validators;
import com.daniel.gestiondestock.dto.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {
    public static List<String> validate (ClientDto dto) {
        List<String> errors = new ArrayList<>();
        if(dto == null){
            errors.add("Veuillez renseigner le 'Nom' du client");
            errors.add("Veuillez renseigner l 'Email' du client");
            errors.add("Veuillez renseigner le 'Nom' du client");
            errors.add("Veuillez renseigner le 'Prenom' du client");
            errors.add("Veuillez renseigner le numero de téléphone du client");
            return errors;
        }

        if(!StringUtils.hasLength(dto.getNom())){
            errors.add("Veuillez renseigner le 'Nom' du client");
        }
        if(!StringUtils.hasLength(dto.getMail())){
            errors.add("Veuillez renseigner l 'Email' du client");
        }
        if(!StringUtils.hasLength(dto.getPrenom())){
            errors.add("Veuillez renseigner le 'Prenom' du client");
        }
        if(!StringUtils.hasLength(dto.getNumTel())){
            errors.add("Veuillez renseigner le numero de téléphone du client");
        }


        return errors;
    }
}
