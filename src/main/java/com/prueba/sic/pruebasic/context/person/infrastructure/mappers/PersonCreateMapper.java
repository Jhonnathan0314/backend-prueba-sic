package com.prueba.sic.pruebasic.context.person.infrastructure.mappers;

import com.prueba.sic.pruebasic.context.person.application.dto.PersonCreateDTO;
import com.prueba.sic.pruebasic.context.person.domain.model.Person;
import com.prueba.sic.pruebasic.context.person.infrastructure.persistence.PersonEntity;
import com.prueba.sic.pruebasic.utils.mappers.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class PersonCreateMapper implements Mapper<PersonEntity, Person, PersonCreateDTO> {

    @Override
    public Person entityToModel(PersonEntity entity) {
        return Person.builder()
                .identificationNumber(entity.getIdentificationNumber())
                .identificationType(entity.getIdentificationType())
                .name(entity.getName())
                .lastname(entity.getLastname())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }

    @Override
    public PersonEntity modelToEntity(Person model) {
        return PersonEntity.builder()
                .identificationNumber(model.getIdentificationNumber())
                .identificationType(model.getIdentificationType())
                .name(model.getName())
                .lastname(model.getLastname())
                .phone(model.getPhone())
                .address(model.getAddress())
                .email(model.getEmail())
                .password(model.getPassword())
                .build();
    }

    @Override
    public PersonCreateDTO modelToDto(Person model) {
        return PersonCreateDTO.builder()
                .identificationNumber(model.getIdentificationNumber())
                .identificationType(model.getIdentificationType())
                .name(model.getName())
                .lastname(model.getLastname())
                .phone(model.getPhone())
                .address(model.getAddress())
                .email(model.getEmail())
                .password(model.getPassword())
                .build();
    }

    @Override
    public Person dtoToModel(PersonCreateDTO dto) {
        return Person.builder()
                .identificationNumber(dto.getIdentificationNumber())
                .identificationType(dto.getIdentificationType())
                .name(dto.getName())
                .lastname(dto.getLastname())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .email(dto.getEmail())
                .password(dto.getPassword())
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
    public List<PersonCreateDTO> modelsToDtos(List<Person> models) {
        return models.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> dtosToModels(List<PersonCreateDTO> dtos) {
        return dtos.stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }

}
