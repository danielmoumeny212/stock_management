package com.daniel.gestiondestock.mapper;
import java.util.List;
import java.util.stream.Collectors;

public class DtoMapper<Entity, Dto> {

    private final EntityConverter<Entity, Dto> fromEntityConverter;
    private final EntityConverter<Dto, Entity> toEntityConverter;

    public DtoMapper(EntityConverter<Entity, Dto> fromEntityConverter, EntityConverter<Dto, Entity> toEntityConverter) {
        this.fromEntityConverter = fromEntityConverter;
        this.toEntityConverter = toEntityConverter;
    }

    public Dto fromEntity(Entity instance) {
        return fromEntityConverter.convert(instance);
    }

    public Entity toEntity(Dto dto) {
        return toEntityConverter.convert(dto);
    }

    public List<Dto> fromEntityList(List<Entity> entityList) {
        return entityList.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    public List<Entity> toEntityList(List<Dto> dtoList) {
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
