package com.prueba.sic.pruebasic.context.employee.application.usecase;

import com.prueba.sic.pruebasic.context.employee.domain.model.Employee;
import com.prueba.sic.pruebasic.context.employee.domain.port.EmployeeRepository;
import com.prueba.sic.pruebasic.utils.constants.ErrorMessages;
import com.prueba.sic.pruebasic.utils.exceptions.NonExistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeDeleteByIdUseCase {

    private final EmployeeRepository employeeRepository;
    private final ErrorMessages errorMessages = new ErrorMessages();

    public void deleteById(Long id) throws NonExistenceException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()) throw new NonExistenceException(errorMessages.NON_EXISTENT_DATA);
        employeeRepository.deleteById(id);
    }

}
