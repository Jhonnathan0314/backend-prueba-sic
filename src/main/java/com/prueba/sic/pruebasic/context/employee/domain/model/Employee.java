package com.prueba.sic.pruebasic.context.employee.domain.model;

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
public class Employee {

    private Long identificationNumber;
    private String dependence;
    private Timestamp admissionDate;
    private Timestamp creationDate;
    private Timestamp updateDate;
    private Person person;

    public boolean isValid(Employee employee) {
        if(employee.getDependence() == null) return false;
        if(employee.getAdmissionDate() == null) return false;
        return !employee.getDependence().isEmpty() &&
                !employee.getAdmissionDate().toString().isEmpty();
    }

}
