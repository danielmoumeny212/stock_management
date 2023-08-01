package com.daniel.gestiondestock.validators;

import com.daniel.gestiondestock.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {
    public static List<String> validate (CategoryDto dto){
        List<String> errors = new ArrayList<>();

        if(dto == null || !StringUtils.hasLength(dto.getCode())){
            errors.add("Veuillez renseigner le code de la categorie");
        }

        return errors;
    }
}
