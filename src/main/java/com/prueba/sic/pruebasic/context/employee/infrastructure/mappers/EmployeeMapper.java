package com.prueba.sic.pruebasic.context.employee.infrastructure.mappers;

import com.prueba.sic.pruebasic.context.employee.application.dto.EmployeeDTO;
import com.prueba.sic.pruebasic.context.employee.domain.model.Employee;
import com.prueba.sic.pruebasic.context.employee.infrastructure.persistence.EmployeeEntity;
import com.prueba.sic.pruebasic.context.person.infrastructure.mappers.PersonMapper;
import com.prueba.sic.pruebasic.utils.mappers.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMapper implements Mapper<EmployeeEntity, Employee, EmployeeDTO> {

    private final PersonMapper personMapper = new PersonMapper();

    @Override
    public Employee entityToModel(EmployeeEntity entity) {
        return Employee.builder()
                .identificationNumber(entity.getIdentificationNumber())
                .dependence(entity.getDependence())
                .admissionDate(entity.getAdmissionDate())
                .person(personMapper.entityToModel(entity.getPerson()))
                .build();
    }

    @Override
    public EmployeeEntity modelToEntity(Employee model) {
        return EmployeeEntity.builder()
                .identificationNumber(model.getIdentificationNumber())
                .dependence(model.getDependence())
                .admissionDate(model.getAdmissionDate())
                .person(personMapper.modelToEntity(model.getPerson()))
                .build();
    }

    @Override
    public EmployeeDTO modelToDto(Employee model) {
        return EmployeeDTO.builder()
                .identificationNumber(model.getIdentificationNumber())
                .dependence(model.getDependence())
                .admissionDate(model.getAdmissionDate())
                .person(personMapper.modelToDto(model.getPerson()))
                .build();
    }

    @Override
    public Employee dtoToModel(EmployeeDTO dto) {
        return Employee.builder()
                .identificationNumber(dto.getIdentificationNumber())
                .dependence(dto.getDependence())
                .admissionDate(dto.getAdmissionDate())
                .person(personMapper.dtoToModel(dto.getPerson()))
                .build();
    }

    @Override
    public List<Employee> entitiesToModels(List<EmployeeEntity> entities) {
        return entities.stream()
                .map(this::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeEntity> modelsToEntities(List<Employee> models) {
        return models.stream()
                .map(this::modelToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> modelsToDtos(List<Employee> models) {
        return models.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> dtosToModels(List<EmployeeDTO> dtos) {
        return dtos.stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }

}
