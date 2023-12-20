package com.prueba.sic.pruebasic.context.employee.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreateDTO {

    private Long identificationNumber;
    private String dependence;
    private Timestamp admissionDate;

}
