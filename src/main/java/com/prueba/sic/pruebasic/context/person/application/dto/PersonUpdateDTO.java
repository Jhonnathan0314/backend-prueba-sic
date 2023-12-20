package com.prueba.sic.pruebasic.context.person.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonUpdateDTO {

    private String identificationType;
    private String name;
    private String lastname;
    private Long phone;
    private String address;
    private String email;
    private String password;

}
