package com.prueba.sic.pruebasic.context.employee.application.usecase;

import com.prueba.sic.pruebasic.context.employee.domain.model.Employee;
import com.prueba.sic.pruebasic.context.employee.domain.port.EmployeeRepository;
import com.prueba.sic.pruebasic.utils.constants.ErrorMessages;
import com.prueba.sic.pruebasic.utils.exceptions.NoResultsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeFindAllUseCase {

    private final EmployeeRepository employeeRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();

    public List<Employee> findAll() throws NoResultsException {
        List<Employee> employees = employeeRepository.findAll();
        if(employees == null || employees.isEmpty()) throw new NoResultsException(errorMessages.NO_RESULTS);
        return employees;
    }

}
