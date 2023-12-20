package com.prueba.sic.pruebasic.security.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    Long identificationNumber;
    String password;

    public boolean isValidRequest(LoginRequest request) {
        if(request.getIdentificationNumber() == null || request.getPassword() == null) return false;
        return !String.valueOf(request.getIdentificationNumber()).isEmpty()  && !request.getPassword().isEmpty();
    }
}
