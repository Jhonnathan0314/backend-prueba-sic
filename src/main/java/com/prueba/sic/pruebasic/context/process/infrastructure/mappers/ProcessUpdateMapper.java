package com.prueba.sic.pruebasic.context.process.infrastructure.mappers;

import com.prueba.sic.pruebasic.context.process.application.dto.ProcessUpdateDTO;
import com.prueba.sic.pruebasic.context.process.domain.model.Process;
import com.prueba.sic.pruebasic.context.process.infrastructure.persistence.ProcessEntity;
import com.prueba.sic.pruebasic.utils.mappers.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class ProcessUpdateMapper implements Mapper<ProcessEntity, Process, ProcessUpdateDTO> {

    @Override
    public Process entityToModel(ProcessEntity entity) {
        return Process.builder()
                .filingYear(entity.getFilingYear())
                .processName(entity.getProcessName())
                .description(entity.getDescription())
                .build();
    }

    @Override
    public ProcessEntity modelToEntity(Process model) {
        return ProcessEntity.builder()
                .filingYear(model.getFilingYear())
                .processName(model.getProcessName())
                .description(model.getDescription())
                .build();
    }

    @Override
    public ProcessUpdateDTO modelToDto(Process model) {
        return ProcessUpdateDTO.builder()
                .filingYear(model.getFilingYear())
                .processName(model.getProcessName())
                .description(model.getDescription())
                .build();
    }

    @Override
    public Process dtoToModel(ProcessUpdateDTO dto) {
        return Process.builder()
                .filingYear(dto.getFilingYear())
                .processName(dto.getProcessName())
                .description(dto.getDescription())
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
    public List<ProcessUpdateDTO> modelsToDtos(List<Process> models) {
        return models.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Process> dtosToModels(List<ProcessUpdateDTO> dtos) {
        return dtos.stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }

}
