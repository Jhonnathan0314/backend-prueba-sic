package com.prueba.sic.pruebasic.context.process.infrastructure.adapter;

import com.prueba.sic.pruebasic.context.process.domain.model.Process;
import com.prueba.sic.pruebasic.context.process.domain.port.ProcessRepository;
import com.prueba.sic.pruebasic.context.process.infrastructure.mappers.ProcessMapper;
import com.prueba.sic.pruebasic.context.process.infrastructure.persistence.ProcessEntity;
import com.prueba.sic.pruebasic.context.process.infrastructure.persistence.ProcessJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProcessRepositoryJpaAdapter implements ProcessRepository {

    private final ProcessJpaRepository processJpaRepository;
    private final ProcessMapper mapper = new ProcessMapper();

    @Override
    public List<Process> findAll() {
        List<ProcessEntity> processEntities = processJpaRepository.findAll();
        return mapper.entitiesToModels(processEntities);
    }

    @Override
    public Optional<Process> findById(Long id) {
        Optional<ProcessEntity> optProcessEntity = processJpaRepository.findById(id);
        return optProcessEntity.map(mapper::entityToModel);
    }

    @Override
    public Process create(Process process) {
        ProcessEntity processEntity = processJpaRepository.save(mapper.modelToEntity(process));
        return mapper.entityToModel(processEntity);
    }

    @Override
    public Process update(Process process) {
        ProcessEntity processEntity = processJpaRepository.save(mapper.modelToEntity(process));
        return mapper.entityToModel(processEntity);
    }

    @Override
    public void deleteById(Long id) {
        processJpaRepository.deleteById(id);
    }

}
