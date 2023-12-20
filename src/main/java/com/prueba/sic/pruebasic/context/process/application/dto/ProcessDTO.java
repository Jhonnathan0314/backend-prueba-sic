package com.prueba.sic.pruebasic.context.process.application.dto;

import com.prueba.sic.pruebasic.context.employee.application.dto.EmployeeDTO;
import com.prueba.sic.pruebasic.context.person.application.dto.PersonDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProcessDTO {

    private Long filingNumber;
    private int filingYear;
    private String processName;
    private String description;
    private PersonDTO filingPerson;
    private EmployeeDTO officialReceived;

}
