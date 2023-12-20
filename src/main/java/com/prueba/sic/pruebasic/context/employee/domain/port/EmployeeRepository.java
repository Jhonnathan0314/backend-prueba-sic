package com.prueba.sic.pruebasic.context.employee.domain.port;

import com.prueba.sic.pruebasic.context.employee.domain.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    List<Employee> findAll();
    Optional<Employee> findById(Long id);
    Employee create(Employee employee);
    Employee update(Employee employee);
    void deleteById(Long id);

}
