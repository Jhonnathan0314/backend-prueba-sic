package com.prueba.sic.pruebasic.context.process.infrastructure.mappers;

import com.prueba.sic.pruebasic.context.employee.infrastructure.mappers.EmployeeMapper;
import com.prueba.sic.pruebasic.context.person.infrastructure.mappers.PersonMapper;
import com.prueba.sic.pruebasic.context.process.application.dto.ProcessDTO;
import com.prueba.sic.pruebasic.context.process.domain.model.Process;
import com.prueba.sic.pruebasic.context.process.infrastructure.persistence.ProcessEntity;
import com.prueba.sic.pruebasic.utils.mappers.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class ProcessMapper implements Mapper<ProcessEntity, Process, ProcessDTO> {

    private final PersonMapper personMapper = new PersonMapper();
    private final EmployeeMapper employeeMapper = new EmployeeMapper();

    @Override
    public Process entityToModel(ProcessEntity entity) {
        return Process.builder()
                .filingNumber(entity.getFilingNumber())
                .filingYear(entity.getFilingYear())
                .processName(entity.getProcessName())
                .description(entity.getDescription())
                .filingPerson(personMapper.entityToModel(entity.getFilingPerson()))
                .officialReceived(employeeMapper.entityToModel(entity.getOfficialReceived()))
                .build();
    }

    @Override
    public ProcessEntity modelToEntity(Process model) {
        return ProcessEntity.builder()
                .filingNumber(model.getFilingNumber())
                .filingYear(model.getFilingYear())
                .processName(model.getProcessName())
                .description(model.getDescription())
                .filingPerson(personMapper.modelToEntity(model.getFilingPerson()))
                .officialReceived(employeeMapper.modelToEntity(model.getOfficialReceived()))
                .build();
    }

    @Override
    public ProcessDTO modelToDto(Process model) {
        return ProcessDTO.builder()
                .filingNumber(model.getFilingNumber())
                .filingYear(model.getFilingYear())
                .processName(model.getProcessName())
                .description(model.getDescription())
                .filingPerson(personMapper.modelToDto(model.getFilingPerson()))
                .officialReceived(employeeMapper.modelToDto(model.getOfficialReceived()))
                .build();
    }

    @Override
    public Process dtoToModel(ProcessDTO dto) {
        return Process.builder()
                .filingNumber(dto.getFilingNumber())
                .filingYear(dto.getFilingYear())
                .processName(dto.getProcessName())
                .description(dto.getDescription())
                .filingPerson(personMapper.dtoToModel(dto.getFilingPerson()))
                .officialReceived(employeeMapper.dtoToModel(dto.getOfficialReceived()))
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
    public List<ProcessDTO> modelsToDtos(List<Process> models) {
        return models.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Process> dtosToModels(List<ProcessDTO> dtos) {
        return dtos.stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }

}
