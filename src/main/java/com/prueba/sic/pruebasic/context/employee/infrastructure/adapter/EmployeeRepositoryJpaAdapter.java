package com.prueba.sic.pruebasic.context.employee.infrastructure.adapter;

import com.prueba.sic.pruebasic.context.employee.domain.model.Employee;
import com.prueba.sic.pruebasic.context.employee.domain.port.EmployeeRepository;
import com.prueba.sic.pruebasic.context.employee.infrastructure.mappers.EmployeeMapper;
import com.prueba.sic.pruebasic.context.employee.infrastructure.persistence.EmployeeEntity;
import com.prueba.sic.pruebasic.context.employee.infrastructure.persistence.EmployeeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryJpaAdapter implements EmployeeRepository {

    private final EmployeeJpaRepository employeeJpaRepository;
    private final EmployeeMapper mapper = new EmployeeMapper();

    @Override
    public List<Employee> findAll() {
        List<EmployeeEntity> employeeEntities = employeeJpaRepository.findAll();
        return mapper.entitiesToModels(employeeEntities);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        Optional<EmployeeEntity> optEmployeeEntity = employeeJpaRepository.findById(id);
        return optEmployeeEntity.map(mapper::entityToModel);
    }

    @Override
    public Employee create(Employee employee) {
        EmployeeEntity employeeEntity = employeeJpaRepository.save(mapper.modelToEntity(employee));
        return mapper.entityToModel(employeeEntity);
    }

    @Override
    public Employee update(Employee employee) {
        EmployeeEntity employeeEntity = employeeJpaRepository.save(mapper.modelToEntity(employee));
        return mapper.entityToModel(employeeEntity);
    }

    @Override
    public void deleteById(Long id) {
        employeeJpaRepository.deleteById(id);
    }

}
