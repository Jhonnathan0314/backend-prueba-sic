package com.prueba.sic.pruebasic.context.process.application.usecase;


import com.prueba.sic.pruebasic.context.process.domain.model.Process;
import com.prueba.sic.pruebasic.context.process.domain.port.ProcessRepository;
import com.prueba.sic.pruebasic.utils.constants.ErrorMessages;
import com.prueba.sic.pruebasic.utils.exceptions.NoResultsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProcessFindByIdUseCase {

    private final ProcessRepository processRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();

    public Process findById(Long id) throws NoResultsException {
        Optional<Process> optionalProcess = processRepository.findById(id);
        if(optionalProcess.isEmpty()) throw new NoResultsException(errorMessages.NO_RESULTS);
        return optionalProcess.get();
    }

}
