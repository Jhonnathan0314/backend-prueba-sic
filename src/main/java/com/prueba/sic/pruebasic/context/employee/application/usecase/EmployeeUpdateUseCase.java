package com.prueba.sic.pruebasic.context.employee.application.usecase;

import com.prueba.sic.pruebasic.context.employee.domain.model.Employee;
import com.prueba.sic.pruebasic.context.employee.domain.port.EmployeeRepository;
import com.prueba.sic.pruebasic.utils.constants.ErrorMessages;
import com.prueba.sic.pruebasic.utils.exceptions.InvalidBodyException;
import com.prueba.sic.pruebasic.utils.exceptions.NoChangesException;
import com.prueba.sic.pruebasic.utils.exceptions.NoIdReceivedException;
import com.prueba.sic.pruebasic.utils.exceptions.NoResultsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeUpdateUseCase {

    private final ErrorMessages errorMessages = new ErrorMessages();

    private final EmployeeRepository employeeRepository;

    public Employee update(Employee employee) throws NoIdReceivedException, NoResultsException, NoChangesException, InvalidBodyException {
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
