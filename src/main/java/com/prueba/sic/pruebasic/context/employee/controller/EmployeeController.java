package com.prueba.sic.pruebasic.context.employee.controller;

import com.prueba.sic.pruebasic.context.employee.application.dto.EmployeeCreateDTO;
import com.prueba.sic.pruebasic.context.employee.application.dto.EmployeeResponseDTO;
import com.prueba.sic.pruebasic.context.employee.application.dto.EmployeeUpdateDTO;
import com.prueba.sic.pruebasic.context.employee.application.usecase.*;
import com.prueba.sic.pruebasic.context.employee.domain.model.Employee;
import com.prueba.sic.pruebasic.context.employee.infrastructure.mappers.EmployeeCreateMapper;
import com.prueba.sic.pruebasic.context.employee.infrastructure.mappers.EmployeeResponseMapper;
import com.prueba.sic.pruebasic.context.employee.infrastructure.mappers.EmployeeUpdateMapper;
import com.prueba.sic.pruebasic.utils.exceptions.*;
import com.prueba.sic.pruebasic.utils.http.HttpUtils;
import com.prueba.sic.pruebasic.utils.messages.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmployeeController {

    private final EmployeeFindAllUseCase employeeFindAllUseCase;
    private final EmployeeFindByIdUseCase employeeFindByIdUseCase;
    private final EmployeeCreateUseCase employeeCreateUseCase;
    private final EmployeeUpdateUseCase employeeUpdateUseCase;
    private final EmployeeDeleteByIdUseCase employeeDeleteByIdUseCase;

    private final EmployeeCreateMapper employeeCreateMapper = new EmployeeCreateMapper();
    private final EmployeeUpdateMapper employeeUpdateMapper = new EmployeeUpdateMapper();
    private final EmployeeResponseMapper employeeResponseMapper = new EmployeeResponseMapper();
    private final HttpUtils httpUtils = new HttpUtils();

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>> findAll() {
        ApiResponse<List<EmployeeResponseDTO>> response = new ApiResponse<>();
        try {
            List<EmployeeResponseDTO> employees = employeeResponseMapper.modelsToDtos(employeeFindAllUseCase.findAll());
            response.setData(employees);
            return ResponseEntity.ok(response);
        } catch (NoResultsException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> findById(@PathVariable Long id) {
        ApiResponse<EmployeeResponseDTO> response = new ApiResponse<>();
        try {
            Employee employee = employeeFindByIdUseCase.findById(id);
            response.setData(employeeResponseMapper.modelToDto(employee));
            return ResponseEntity.ok(response);
        } catch (NoResultsException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> create(@RequestBody EmployeeCreateDTO employee) {
        ApiResponse<EmployeeResponseDTO> response = new ApiResponse<>();
        try {
            response.setData(employeeResponseMapper.modelToDto(employeeCreateUseCase.create(employeeCreateMapper.dtoToModel(employee))));
            return ResponseEntity.ok(response);
        } catch (DuplicatedException | InvalidBodyException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> update(@RequestBody EmployeeUpdateDTO employee) {
        ApiResponse<EmployeeResponseDTO> response = new ApiResponse<>();
        try {
            response.setData(employeeResponseMapper.modelToDto(employeeUpdateUseCase.update(employeeUpdateMapper.dtoToModel(employee))));
            return ResponseEntity.ok(response);
        } catch (NoIdReceivedException | InvalidBodyException | NoResultsException | NoChangesException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteById(@PathVariable Long id) {
        ApiResponse<Object> response = new ApiResponse<>();
        try {
            employeeDeleteByIdUseCase.deleteById(id);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (NonExistenceException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

}
