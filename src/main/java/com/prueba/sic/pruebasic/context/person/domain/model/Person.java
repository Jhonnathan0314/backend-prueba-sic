package com.prueba.sic.pruebasic.context.person.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private Long identificationNumber;
    private String identificationType;
    private String name;
    private String lastname;
    private Long phone;
    private String address;
    private String email;
    private Timestamp creationDate;
    private Timestamp updateDate;

    public boolean isValid(Person person) {
        if(person.getIdentificationType() == null) return false;
        if(person.getName() == null) return false;
        if(person.getLastname() == null) return false;
        if(person.getPhone() == null) return false;
        if(person.getAddress() == null) return false;
        if(person.getEmail() == null) return false;
        return !person.getIdentificationType().isEmpty() &&
                !person.getName().isEmpty() &&
                !person.getLastname().isEmpty() &&
                !person.getPhone().toString().isEmpty() &&
                !person.getAddress().isEmpty() &&
                !person.getEmail().isEmpty();
    }

}
