package com.daniel.gestiondestock.mapper;
import org.modelmapper.ModelMapper;

public class DtoMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static <D, E> D fromEntity(E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public static <D, E> E toEntity(D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }
}
