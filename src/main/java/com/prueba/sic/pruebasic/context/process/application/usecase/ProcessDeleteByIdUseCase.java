package com.prueba.sic.pruebasic.context.process.application.usecase;

import com.prueba.sic.pruebasic.context.process.domain.model.Process;
import com.prueba.sic.pruebasic.context.process.domain.port.ProcessRepository;
import com.prueba.sic.pruebasic.utils.constants.ErrorMessages;
import com.prueba.sic.pruebasic.utils.exceptions.NonExistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProcessDeleteByIdUseCase {

    private final ProcessRepository processRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();

    public void deleteById(Long id) throws NonExistenceException {
        Optional<Process> process = processRepository.findById(id);
        if(process.isEmpty()) throw new NonExistenceException(errorMessages.NON_EXISTENT_DATA);
        processRepository.deleteById(id);
    }

}
