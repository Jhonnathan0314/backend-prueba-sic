package com.prueba.sic.pruebasic.context.process.application.usecase;

import com.prueba.sic.pruebasic.context.process.domain.model.Process;
import com.prueba.sic.pruebasic.context.process.domain.port.ProcessRepository;
import com.prueba.sic.pruebasic.utils.constants.ErrorMessages;
import com.prueba.sic.pruebasic.utils.exceptions.InvalidBodyException;
import com.prueba.sic.pruebasic.utils.exceptions.NoChangesException;
import com.prueba.sic.pruebasic.utils.exceptions.NoIdReceivedException;
import com.prueba.sic.pruebasic.utils.exceptions.NoResultsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProcessUpdateUseCase {

    private final ErrorMessages errorMessages = new ErrorMessages();

    private final ProcessRepository processRepository;

    public Process update(Process process) throws NoIdReceivedException, NoResultsException, NoChangesException, InvalidBodyException {
        if(process.getFilingNumber() == null) throw new NoIdReceivedException(errorMessages.NO_ID_RECEIVED);

        if(!process.isValid(process)) throw new InvalidBodyException(errorMessages.INVALID_BODY);

        Optional<Process> optProcess = processRepository.findById(process.getFilingNumber());
        if(optProcess.isEmpty()) throw new NoResultsException(errorMessages.NO_RESULTS);

        Process processDb = optProcess.get();
        if(areEquals(processDb, process)) throw new NoChangesException(errorMessages.NO_CHANGES);

        process.setCreationDate(processDb.getCreationDate());
        return processRepository.update(process);
    }

    private boolean areEquals(Process oldProcess, Process newProcess) {
        return oldProcess.getFilingYear() == newProcess.getFilingYear() &&
                oldProcess.getProcessName().equals(newProcess.getProcessName()) &&
                oldProcess.getDescription().equals(newProcess.getDescription());
    }

}
