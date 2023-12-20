package com.prueba.sic.pruebasic.context.process.domain.port;

import com.prueba.sic.pruebasic.context.process.domain.model.Process;

import java.util.List;
import java.util.Optional;

public interface ProcessRepository {

    List<Process> findAll();
    Optional<Process> findById(Long id);
    Process create(Process process);
    Process update(Process process);
    void deleteById(Long id);

}
