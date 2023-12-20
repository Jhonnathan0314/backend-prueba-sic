package com.prueba.sic.pruebasic.context.process.domain.model;

import com.prueba.sic.pruebasic.context.employee.domain.model.Employee;
import com.prueba.sic.pruebasic.context.person.domain.model.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Process {

    private Long filingNumber;
    private String filingYear;
    private String processName;
    private String description;
    private Timestamp creationDate;
    private Timestamp updateDate;
    private Person filingPerson;
    private Employee officialReceived;

    public boolean isValid(Process process) {
        if(process.getFilingYear() == null) return false;
        if(process.getProcessName() == null) return false;
        if(process.getDescription() == null) return false;
        return !process.getFilingYear().isEmpty() &&
                !process.getProcessName().isEmpty() &&
                !process.getDescription().isEmpty();
    }

}
