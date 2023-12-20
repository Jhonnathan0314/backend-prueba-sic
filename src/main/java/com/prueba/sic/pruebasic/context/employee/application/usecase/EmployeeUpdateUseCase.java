package com.prueba.sic.pruebasic.context.employee.application.usecase;

import com.prueba.sic.pruebasic.context.employee.domain.model.Employee;
import com.prueba.sic.pruebasic.context.employee.domain.port.EmployeeRepository;
import com.prueba.sic.pruebasic.context.person.domain.model.Person;
import com.prueba.sic.pruebasic.context.person.domain.port.PersonRepository;
import com.prueba.sic.pruebasic.utils.constants.ErrorMessages;
import com.prueba.sic.pruebasic.utils.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeUpdateUseCase {

    private final ErrorMessages errorMessages = new ErrorMessages();
    private final EmployeeRepository employeeRepository;
    private final PersonRepository personRepository;

    public Employee update(Long id, Employee employee) throws NoIdReceivedException, NoResultsException, NoChangesException, InvalidBodyException, NonExistenceException {

        employee.setIdentificationNumber(id);

        Optional<Person> optionalPerson = personRepository.findById(employee.getIdentificationNumber());
        if(optionalPerson.isEmpty()) throw new NonExistenceException(errorMessages.PERSON_EMPLOYEE_ID_ERROR);

        employee.setPerson(optionalPerson.get());

        if(employee.getIdentificationNumber() == null) throw new NoIdReceivedException(errorMessages.NO_ID_RECEIVED);

        if(!employee.isValid(employee)) throw new InvalidBodyException(errorMessages.INVALID_BODY);

        Optional<Employee> optEmployee = employeeRepository.findById(employee.getIdentificationNumber());
        if(optEmployee.isEmpty()) throw new NoResultsException(errorMessages.NO_RESULTS);

        Employee employeeDb = optEmployee.get();
        if(areEquals(employeeDb, employee)) throw new NoChangesException(errorMessages.NO_CHANGES);

        employee.setCreationDate(employeeDb.getCreationDate());
        return employeeRepository.update(employee);
    }

    private boolean areEquals(Employee oldEmployee, Employee newEmployee) {
        return oldEmployee.getDependence().equals(newEmployee.getDependence()) &&
                oldEmployee.getAdmissionDate().equals(newEmployee.getAdmissionDate());
    }

}
