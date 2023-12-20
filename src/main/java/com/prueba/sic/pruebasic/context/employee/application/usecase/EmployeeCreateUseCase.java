package com.prueba.sic.pruebasic.context.employee.application.usecase;

import com.prueba.sic.pruebasic.context.employee.domain.model.Employee;
import com.prueba.sic.pruebasic.context.employee.domain.port.EmployeeRepository;
import com.prueba.sic.pruebasic.utils.constants.ErrorMessages;
import com.prueba.sic.pruebasic.utils.exceptions.DuplicatedException;
import com.prueba.sic.pruebasic.utils.exceptions.InvalidBodyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeCreateUseCase {

    private final EmployeeRepository employeeRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();

    public Employee create(Employee employee) throws DuplicatedException, InvalidBodyException {
        if(!employee.isValid(employee)) throw new InvalidBodyException(errorMessages.INVALID_BODY);
        if(employeeRepository.findById(employee.getIdentificationNumber()).isPresent()) throw new DuplicatedException(errorMessages.DUPLICATED);
        return employeeRepository.create(employee);
    }

}
