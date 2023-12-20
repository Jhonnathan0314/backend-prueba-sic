package com.prueba.sic.pruebasic.context.employee.application.usecase;

import com.prueba.sic.pruebasic.context.employee.domain.model.Employee;
import com.prueba.sic.pruebasic.context.employee.domain.port.EmployeeRepository;
import com.prueba.sic.pruebasic.context.person.domain.model.Person;
import com.prueba.sic.pruebasic.context.person.domain.port.PersonRepository;
import com.prueba.sic.pruebasic.utils.constants.ErrorMessages;
import com.prueba.sic.pruebasic.utils.exceptions.DuplicatedException;
import com.prueba.sic.pruebasic.utils.exceptions.InvalidBodyException;
import com.prueba.sic.pruebasic.utils.exceptions.NonExistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeCreateUseCase {

    private final EmployeeRepository employeeRepository;
    private final PersonRepository personRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();

    public Employee create(Employee employee) throws DuplicatedException, InvalidBodyException, NonExistenceException {

        Optional<Person> optionalPerson = personRepository.findById(employee.getIdentificationNumber());
        if(optionalPerson.isEmpty()) throw new NonExistenceException(errorMessages.PERSON_EMPLOYEE_ID_ERROR);

        employee.setPerson(optionalPerson.get());

        if(!employee.isValid(employee)) throw new InvalidBodyException(errorMessages.INVALID_BODY);

        if(employeeRepository.findById(employee.getIdentificationNumber()).isPresent()) throw new DuplicatedException(errorMessages.DUPLICATED);
        return employeeRepository.create(employee);
    }

}
