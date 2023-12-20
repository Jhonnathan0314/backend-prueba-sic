package com.prueba.sic.pruebasic.security.service;

import com.prueba.sic.pruebasic.context.person.application.usecase.PersonCreateUseCase;
import com.prueba.sic.pruebasic.context.person.application.usecase.PersonFindByIdUseCase;
import com.prueba.sic.pruebasic.context.person.domain.model.Person;
import com.prueba.sic.pruebasic.security.jwt.JwtService;
import com.prueba.sic.pruebasic.security.models.AuthResponse;
import com.prueba.sic.pruebasic.security.models.LoginRequest;
import com.prueba.sic.pruebasic.utils.constants.ErrorMessages;
import com.prueba.sic.pruebasic.utils.exceptions.DuplicatedException;
import com.prueba.sic.pruebasic.utils.exceptions.InvalidBodyException;
import com.prueba.sic.pruebasic.utils.exceptions.NoResultsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final ErrorMessages errorMessages = new ErrorMessages();
    private final PersonFindByIdUseCase personFindByIdUseCase;
    private final PersonCreateUseCase personCreateUseCase;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) throws NoResultsException, InvalidBodyException {

        if(!request.isValidRequest(request)) throw new InvalidBodyException(errorMessages.INVALID_BODY);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getIdentificationNumber(), request.getPassword()));
        Person personDb = personFindByIdUseCase.findById(request.getIdentificationNumber());

        if(personDb == null) throw new NoResultsException(errorMessages.NO_RESULTS);

        Map<String, String> extraClaims = new HashMap<>();

        String token = jwtService.generateToken(personDb, extraClaims);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(Person request) throws InvalidBodyException, DuplicatedException {
        Person person = Person.builder()
                .identificationNumber(request.getIdentificationNumber())
                .identificationType(request.getIdentificationType())
                .name(request.getName())
                .lastname(request.getLastname())
                .phone(request.getPhone())
                .address(request.getAddress())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        person = personCreateUseCase.create(person);

        Map<String, String> extraClaims = new HashMap<>();

        return AuthResponse.builder()
                .token(jwtService.generateToken(person, extraClaims))
                .build();
    }

}
