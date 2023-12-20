package com.prueba.sic.pruebasic.context.person.infrastructure.mappers;

import com.prueba.sic.pruebasic.context.person.application.dto.PersonUpdateDTO;
import com.prueba.sic.pruebasic.context.person.domain.model.Person;
import com.prueba.sic.pruebasic.context.person.infrastructure.persistence.PersonEntity;
import com.prueba.sic.pruebasic.utils.mappers.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class PersonUpdateMapper implements Mapper<PersonEntity, Person, PersonUpdateDTO> {

    @Override
    public Person entityToModel(PersonEntity entity) {
        return Person.builder()
                .identificationType(entity.getIdentificationType())
                .name(entity.getName())
                .lastname(entity.getLastname())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .email(entity.getEmail())
                .build();
    }

    @Override
    public PersonEntity modelToEntity(Person model) {
        return PersonEntity.builder()
                .identificationType(model.getIdentificationType())
                .name(model.getName())
                .lastname(model.getLastname())
                .phone(model.getPhone())
                .address(model.getAddress())
                .email(model.getEmail())
                .build();
    }

    @Override
    public PersonUpdateDTO modelToDto(Person model) {
        return PersonUpdateDTO.builder()
                .identificationType(model.getIdentificationType())
                .name(model.getName())
                .lastname(model.getLastname())
                .phone(model.getPhone())
                .address(model.getAddress())
                .email(model.getEmail())
                .build();
    }

    @Override
    public Person dtoToModel(PersonUpdateDTO dto) {
        return Person.builder()
                .identificationType(dto.getIdentificationType())
                .name(dto.getName())
                .lastname(dto.getLastname())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .email(dto.getEmail())
                .build();
    }

    @Override
    public List<Person> entitiesToModels(List<PersonEntity> entities) {
        return entities.stream()
                .map(this::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonEntity> modelsToEntities(List<Person> models) {
        return models.stream()
                .map(this::modelToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonUpdateDTO> modelsToDtos(List<Person> models) {
        return models.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> dtosToModels(List<PersonUpdateDTO> dtos) {
        return dtos.stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }

}
