package com.prueba.sic.pruebasic.context.process.application.usecase;

import com.prueba.sic.pruebasic.context.employee.domain.model.Employee;
import com.prueba.sic.pruebasic.context.employee.domain.port.EmployeeRepository;
import com.prueba.sic.pruebasic.context.person.domain.model.Person;
import com.prueba.sic.pruebasic.context.person.domain.port.PersonRepository;
import com.prueba.sic.pruebasic.context.process.domain.model.Process;
import com.prueba.sic.pruebasic.context.process.domain.port.ProcessRepository;
import com.prueba.sic.pruebasic.utils.constants.ErrorMessages;
import com.prueba.sic.pruebasic.utils.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProcessUpdateUseCase {

    private final ErrorMessages errorMessages = new ErrorMessages();
    private final ProcessRepository processRepository;
    private final PersonRepository personRepository;
    private final EmployeeRepository employeeRepository;

    public Process update(Long processId, Long personId, Long employeeId, Process process) throws NoIdReceivedException, NoResultsException, NoChangesException, InvalidBodyException, NonExistenceException {

        process.setFilingNumber(processId);

        Optional<Person> optionalPerson = personRepository.findById(personId);
        if(optionalPerson.isEmpty()) throw new NonExistenceException(errorMessages.PERSON_EMPLOYEE_ID_ERROR);

        process.setFilingPerson(optionalPerson.get());

        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if(optionalEmployee.isEmpty()) throw new NonExistenceException(errorMessages.EMPLOYEE_NOT_FOUND);

        process.setOfficialReceived(optionalEmployee.get());

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
        return oldProcess.getFilingYear().equals(newProcess.getFilingYear()) &&
                oldProcess.getProcessName().equals(newProcess.getProcessName()) &&
                oldProcess.getDescription().equals(newProcess.getDescription());
    }

}
