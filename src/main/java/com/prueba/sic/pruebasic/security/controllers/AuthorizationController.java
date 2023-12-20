package com.prueba.sic.pruebasic.security.controllers;

import com.prueba.sic.pruebasic.context.person.application.dto.PersonCreateDTO;
import com.prueba.sic.pruebasic.context.person.infrastructure.mappers.PersonCreateMapper;
import com.prueba.sic.pruebasic.security.models.AuthResponse;
import com.prueba.sic.pruebasic.security.models.LoginRequest;
import com.prueba.sic.pruebasic.security.service.AuthorizationService;
import com.prueba.sic.pruebasic.utils.exceptions.DuplicatedException;
import com.prueba.sic.pruebasic.utils.exceptions.InvalidBodyException;
import com.prueba.sic.pruebasic.utils.exceptions.NoResultsException;
import com.prueba.sic.pruebasic.utils.http.HttpUtils;
import com.prueba.sic.pruebasic.utils.messages.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthorizationController {

    private final AuthorizationService authService;
    private final HttpUtils httpUtils = new HttpUtils();
    private final PersonCreateMapper personCreateMapper = new PersonCreateMapper();

    @PostMapping(value = "login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginRequest request) {
        ApiResponse<AuthResponse> response = new ApiResponse<>();
        try {
            response.setData(authService.login(request));
            return ResponseEntity.ok(response);
        } catch (NoResultsException | InvalidBodyException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

    @PostMapping(value = "register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@RequestBody PersonCreateDTO request) {
        ApiResponse<AuthResponse> response = new ApiResponse<>();
        try {
            response.setData(authService.register(personCreateMapper.dtoToModel(request)));
            return ResponseEntity.ok(response);
        } catch (InvalidBodyException | DuplicatedException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

}
