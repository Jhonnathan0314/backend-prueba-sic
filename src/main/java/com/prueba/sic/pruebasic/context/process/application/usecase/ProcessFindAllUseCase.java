package com.prueba.sic.pruebasic.context.process.application.usecase;

import com.prueba.sic.pruebasic.context.process.domain.model.Process;
import com.prueba.sic.pruebasic.context.process.domain.port.ProcessRepository;
import com.prueba.sic.pruebasic.utils.constants.ErrorMessages;
import com.prueba.sic.pruebasic.utils.exceptions.NoResultsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessFindAllUseCase {

    private final ProcessRepository processRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();

    public List<Process> findAll() throws NoResultsException {
        List<Process> processes = processRepository.findAll();
        if(processes == null || processes.isEmpty()) throw new NoResultsException(errorMessages.NO_RESULTS);
        return processes;
    }

}
