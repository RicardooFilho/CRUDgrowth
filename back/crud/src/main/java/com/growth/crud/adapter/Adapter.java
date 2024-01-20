package com.growth.crud.adapter;

public interface Adapter<Dto, Entity> {

    Dto toDto(Entity entity);

    Entity toEntity(Dto dto);
}
