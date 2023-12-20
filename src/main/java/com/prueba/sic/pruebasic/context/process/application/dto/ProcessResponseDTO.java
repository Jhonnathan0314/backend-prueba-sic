package com.prueba.sic.pruebasic.context.process.application.dto;

import com.prueba.sic.pruebasic.context.employee.application.dto.EmployeeResponseDTO;
import com.prueba.sic.pruebasic.context.person.application.dto.PersonResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProcessResponseDTO {

    private Long filingNumber;
    private int filingYear;
    private String processName;
    private String description;
    private PersonResponseDTO filingPerson;
    private EmployeeResponseDTO officialReceived;

}
