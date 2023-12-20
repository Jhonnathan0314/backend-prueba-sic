package com.prueba.sic.pruebasic.context.employee.application.dto;

import com.prueba.sic.pruebasic.context.person.application.dto.PersonDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
public class EmployeeDTO {

    private Long identificationNumber;
    private String dependence;
    private Timestamp admissionDate;
    private PersonDTO person;

}
