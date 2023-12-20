package com.prueba.sic.pruebasic.context.employee.application.usecase;


import com.prueba.sic.pruebasic.context.employee.domain.model.Employee;
import com.prueba.sic.pruebasic.context.employee.domain.port.EmployeeRepository;
import com.prueba.sic.pruebasic.utils.constants.ErrorMessages;
import com.prueba.sic.pruebasic.utils.exceptions.NoResultsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeFindByIdUseCase {

    private final EmployeeRepository employeeRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();

    public Employee findById(Long id) throws NoResultsException {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isEmpty()) throw new NoResultsException(errorMessages.NO_RESULTS);
        return optionalEmployee.get();
    }

}
