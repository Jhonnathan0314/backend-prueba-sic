package com.prueba.sic.pruebasic.context.employee.infrastructure.mappers;

import com.prueba.sic.pruebasic.context.employee.application.dto.EmployeeCreateDTO;
import com.prueba.sic.pruebasic.context.employee.domain.model.Employee;
import com.prueba.sic.pruebasic.context.employee.infrastructure.persistence.EmployeeEntity;
import com.prueba.sic.pruebasic.utils.mappers.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeCreateMapper implements Mapper<EmployeeEntity, Employee, EmployeeCreateDTO> {

    @Override
    public Employee entityToModel(EmployeeEntity entity) {
        return Employee.builder()
                .identificationNumber(entity.getIdentificationNumber())
                .dependence(entity.getDependence())
                .admissionDate(entity.getAdmissionDate())
                .build();
    }

    @Override
    public EmployeeEntity modelToEntity(Employee model) {
        return EmployeeEntity.builder()
                .identificationNumber(model.getIdentificationNumber())
                .dependence(model.getDependence())
                .admissionDate(model.getAdmissionDate())
                .build();
    }

    @Override
    public EmployeeCreateDTO modelToDto(Employee model) {
        return EmployeeCreateDTO.builder()
                .identificationNumber(model.getIdentificationNumber())
                .dependence(model.getDependence())
                .admissionDate(model.getAdmissionDate())
                .build();
    }

    @Override
    public Employee dtoToModel(EmployeeCreateDTO dto) {
        return Employee.builder()
                .identificationNumber(dto.getIdentificationNumber())
                .dependence(dto.getDependence())
                .admissionDate(dto.getAdmissionDate())
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
    public List<EmployeeCreateDTO> modelsToDtos(List<Employee> models) {
        return models.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> dtosToModels(List<EmployeeCreateDTO> dtos) {
        return dtos.stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }

}
