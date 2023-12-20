package com.prueba.sic.pruebasic.context.person.presentation.controller;

import com.prueba.sic.pruebasic.context.person.application.dto.PersonCreateDTO;
import com.prueba.sic.pruebasic.context.person.application.dto.PersonResponseDTO;
import com.prueba.sic.pruebasic.context.person.application.dto.PersonUpdateDTO;
import com.prueba.sic.pruebasic.context.person.application.usecase.*;
import com.prueba.sic.pruebasic.context.person.domain.model.Person;
import com.prueba.sic.pruebasic.context.person.infrastructure.mappers.PersonCreateMapper;
import com.prueba.sic.pruebasic.context.person.infrastructure.mappers.PersonResponseMapper;
import com.prueba.sic.pruebasic.context.person.infrastructure.mappers.PersonUpdateMapper;
import com.prueba.sic.pruebasic.utils.exceptions.*;
import com.prueba.sic.pruebasic.utils.http.HttpUtils;
import com.prueba.sic.pruebasic.utils.messages.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PersonController {

    private final PersonFindAllUseCase personFindAllUseCase;
    private final PersonFindByIdUseCase personFindByIdUseCase;
    private final PersonCreateUseCase personCreateUseCase;
    private final PersonUpdateUseCase personUpdateUseCase;
    private final PersonDeleteByIdUseCase personDeleteByIdUseCase;

    private final PersonCreateMapper personCreateMapper = new PersonCreateMapper();
    private final PersonUpdateMapper personUpdateMapper = new PersonUpdateMapper();
    private final PersonResponseMapper personResponseMapper = new PersonResponseMapper();
    private final HttpUtils httpUtils = new HttpUtils();

    @GetMapping
    public ResponseEntity<ApiResponse<List<PersonResponseDTO>>> findAll() {
        ApiResponse<List<PersonResponseDTO>> response = new ApiResponse<>();
        try {
            List<PersonResponseDTO> people = personResponseMapper.modelsToDtos(personFindAllUseCase.findAll());
            response.setData(people);
            return ResponseEntity.ok(response);
        } catch (NoResultsException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PersonResponseDTO>> findById(@PathVariable Long id) {
        ApiResponse<PersonResponseDTO> response = new ApiResponse<>();
        try {
            Person person = personFindByIdUseCase.findById(id);
            response.setData(personResponseMapper.modelToDto(person));
            return ResponseEntity.ok(response);
        } catch (NoResultsException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PersonResponseDTO>> create(@RequestBody PersonCreateDTO person) {
        ApiResponse<PersonResponseDTO> response = new ApiResponse<>();
        try {
            response.setData(personResponseMapper.modelToDto(personCreateUseCase.create(personCreateMapper.dtoToModel(person))));
            return ResponseEntity.ok(response);
        } catch (DuplicatedException | InvalidBodyException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PersonResponseDTO>> update(@PathVariable Long id, @RequestBody PersonUpdateDTO person) {
        ApiResponse<PersonResponseDTO> response = new ApiResponse<>();
        try {
            response.setData(personResponseMapper.modelToDto(personUpdateUseCase.update(id, personUpdateMapper.dtoToModel(person))));
            return ResponseEntity.ok(response);
        } catch (NoIdReceivedException | InvalidBodyException | NoResultsException | NoChangesException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteById(@PathVariable Long id) {
        ApiResponse<Object> response = new ApiResponse<>();
        try {
            personDeleteByIdUseCase.deleteById(id);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (NonExistenceException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

}
