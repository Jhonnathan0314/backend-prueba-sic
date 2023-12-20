package com.prueba.sic.pruebasic.context.process.infrastructure.mappers;

import com.prueba.sic.pruebasic.context.employee.infrastructure.mappers.EmployeeResponseMapper;
import com.prueba.sic.pruebasic.context.person.infrastructure.mappers.PersonResponseMapper;
import com.prueba.sic.pruebasic.context.process.application.dto.ProcessResponseDTO;
import com.prueba.sic.pruebasic.context.process.domain.model.Process;
import com.prueba.sic.pruebasic.context.process.infrastructure.persistence.ProcessEntity;
import com.prueba.sic.pruebasic.utils.mappers.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class ProcessResponseMapper implements Mapper<ProcessEntity, Process, ProcessResponseDTO> {

    private final PersonResponseMapper personResponseMapper = new PersonResponseMapper();
    private final EmployeeResponseMapper employeeResponseMapper = new EmployeeResponseMapper();

    @Override
    public Process entityToModel(ProcessEntity entity) {
        return Process.builder()
                .filingNumber(entity.getFilingNumber())
                .filingYear(entity.getFilingYear())
                .processName(entity.getProcessName())
                .description(entity.getDescription())
                .filingPerson(personResponseMapper.entityToModel(entity.getFilingPerson()))
                .officialReceived(employeeResponseMapper.entityToModel(entity.getOfficialReceived()))
                .build();
    }

    @Override
    public ProcessEntity modelToEntity(Process model) {
        return ProcessEntity.builder()
                .filingNumber(model.getFilingNumber())
                .filingYear(model.getFilingYear())
                .processName(model.getProcessName())
                .description(model.getDescription())
                .filingPerson(personResponseMapper.modelToEntity(model.getFilingPerson()))
                .officialReceived(employeeResponseMapper.modelToEntity(model.getOfficialReceived()))
                .build();
    }

    @Override
    public ProcessResponseDTO modelToDto(Process model) {
        return ProcessResponseDTO.builder()
                .filingNumber(model.getFilingNumber())
                .filingYear(model.getFilingYear())
                .processName(model.getProcessName())
                .description(model.getDescription())
                .filingPerson(personResponseMapper.modelToDto(model.getFilingPerson()))
                .officialReceived(employeeResponseMapper.modelToDto(model.getOfficialReceived()))
                .build();
    }

    @Override
    public Process dtoToModel(ProcessResponseDTO dto) {
        return Process.builder()
                .filingNumber(dto.getFilingNumber())
                .filingYear(dto.getFilingYear())
                .processName(dto.getProcessName())
                .description(dto.getDescription())
                .filingPerson(personResponseMapper.dtoToModel(dto.getFilingPerson()))
                .officialReceived(employeeResponseMapper.dtoToModel(dto.getOfficialReceived()))
                .build();
    }

    @Override
    public List<Process> entitiesToModels(List<ProcessEntity> entities) {
        return entities.stream()
                .map(this::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProcessEntity> modelsToEntities(List<Process> models) {
        return models.stream()
                .map(this::modelToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProcessResponseDTO> modelsToDtos(List<Process> models) {
        return models.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Process> dtosToModels(List<ProcessResponseDTO> dtos) {
        return dtos.stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }

}
