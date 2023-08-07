package com.daniel.gestiondestock.validators;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DtoValidator {

    public static <T> List<String> validate(T dto, String... excludeFields) {
        List<String> errors = new ArrayList<>();
        Class<?> clazz = dto.getClass();
        Field[] fields = clazz.getDeclaredFields();
        List<String> excludedFields = Arrays.asList(excludeFields);

        for (Field field : fields) {
            String fieldName = field.getName();

            if (excludedFields.contains(fieldName)) {
                continue; 
            }

            field.setAccessible(true); // Permet d'accéder aux champs privés
            Object value;
            try {
                value = field.get(dto);
            } catch (IllegalAccessException e) {
                value = null;
            }

            // Ici, vous pouvez implémenter vos règles de validation pour chaque champ
            // Par exemple, vérifier si un champ obligatoire est null, ou si une valeur est
            // hors limites, etc.
            // Ajoutez les erreurs détectées à la liste "errors".

            // Exemple de validation pour un champ obligatoire non null
            if (value == null) {
                errors.add("Field " + fieldName + " of type "  + field.getType().getSimpleName() + " is Required.");
            } else if (value.getClass().getPackage().getName().startsWith(dto.getClass().getPackage().getName())) {
                // Si le champ est un autre DTO du même package, nous le validons récursivement
                List<String> nestedErrors = validate(value, excludeFields);
                for (String nestedError : nestedErrors) {
                    errors.add("In Field ' " + fieldName + "', " + nestedError);
                }
            }
        }

        return errors;
    }
}
