package com.prueba.sic.pruebasic.context.process.application.usecase;

import com.prueba.sic.pruebasic.context.employee.domain.model.Employee;
import com.prueba.sic.pruebasic.context.employee.domain.port.EmployeeRepository;
import com.prueba.sic.pruebasic.context.person.domain.model.Person;
import com.prueba.sic.pruebasic.context.person.domain.port.PersonRepository;
import com.prueba.sic.pruebasic.context.process.domain.model.Process;
import com.prueba.sic.pruebasic.context.process.domain.port.ProcessRepository;
import com.prueba.sic.pruebasic.utils.constants.ErrorMessages;
import com.prueba.sic.pruebasic.utils.exceptions.DuplicatedException;
import com.prueba.sic.pruebasic.utils.exceptions.InvalidBodyException;
import com.prueba.sic.pruebasic.utils.exceptions.NonExistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProcessCreateUseCase {

    private final ProcessRepository processRepository;
    private final PersonRepository personRepository;
    private final EmployeeRepository employeeRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();

    public Process create(Long personId, Long employeeId, Process process) throws DuplicatedException, InvalidBodyException, NonExistenceException {

        Optional<Person> optionalPerson = personRepository.findById(personId);
        if(optionalPerson.isEmpty()) throw new NonExistenceException(errorMessages.PERSON_EMPLOYEE_ID_ERROR);

        process.setFilingPerson(optionalPerson.get());

        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if(optionalEmployee.isEmpty()) throw new NonExistenceException(errorMessages.EMPLOYEE_NOT_FOUND);

        process.setOfficialReceived(optionalEmployee.get());

        if(!process.isValid(process)) throw new InvalidBodyException(errorMessages.INVALID_BODY);

        return processRepository.create(process);
    }

}
