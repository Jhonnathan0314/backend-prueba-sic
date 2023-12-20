package com.prueba.sic.pruebasic.context.process.application.usecase;

import com.prueba.sic.pruebasic.context.process.domain.model.Process;
import com.prueba.sic.pruebasic.context.process.domain.port.ProcessRepository;
import com.prueba.sic.pruebasic.utils.constants.ErrorMessages;
import com.prueba.sic.pruebasic.utils.exceptions.DuplicatedException;
import com.prueba.sic.pruebasic.utils.exceptions.InvalidBodyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessCreateUseCase {

    private final ProcessRepository processRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();

    public Process create(Process process) throws DuplicatedException, InvalidBodyException {
        if(!process.isValid(process)) throw new InvalidBodyException(errorMessages.INVALID_BODY);
        if(processRepository.findById(process.getFilingNumber()).isPresent()) throw new DuplicatedException(errorMessages.DUPLICATED);
        return processRepository.create(process);
    }

}
