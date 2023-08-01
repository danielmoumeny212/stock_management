package com.daniel.gestiondestock.dto;

import com.daniel.gestiondestock.model.Roles;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolesDto {
    private Integer id;


    private String roleName;

    private UtilisateurDto utilisateur;

    public static RolesDto fromEntity(Roles instance){
        return RolesDto.builder()
                .id(instance.getId())
                .utilisateur(UtilisateurDto.fromEntity(instance.getUtilisateur()))
                .roleName(instance.getRoleName())
                .build();
    }

    public static Roles toEntity (RolesDto dto){
        if (dto == null) {
            return null;
        }
        var role = new Roles();
        role.setId(dto.getId());
        role.setRoleName(dto.getRoleName());
        role.setUtilisateur(UtilisateurDto.toEntity(dto.getUtilisateur()));
        return role;
    }
}
