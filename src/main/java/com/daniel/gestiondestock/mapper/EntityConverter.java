package com.daniel.gestiondestock.mapper;


@FunctionalInterface
public interface EntityConverter<Entity, Dto> {
    Dto convert(Entity entity);
}
