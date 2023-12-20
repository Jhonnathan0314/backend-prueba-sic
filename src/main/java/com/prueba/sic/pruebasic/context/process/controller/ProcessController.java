package com.prueba.sic.pruebasic.context.process.controller;

import com.prueba.sic.pruebasic.context.process.application.dto.ProcessCreateDTO;
import com.prueba.sic.pruebasic.context.process.application.dto.ProcessResponseDTO;
import com.prueba.sic.pruebasic.context.process.application.dto.ProcessUpdateDTO;
import com.prueba.sic.pruebasic.context.process.application.usecase.*;
import com.prueba.sic.pruebasic.context.process.domain.model.Process;
import com.prueba.sic.pruebasic.context.process.infrastructure.mappers.ProcessCreateMapper;
import com.prueba.sic.pruebasic.context.process.infrastructure.mappers.ProcessResponseMapper;
import com.prueba.sic.pruebasic.context.process.infrastructure.mappers.ProcessUpdateMapper;
import com.prueba.sic.pruebasic.utils.exceptions.*;
import com.prueba.sic.pruebasic.utils.http.HttpUtils;
import com.prueba.sic.pruebasic.utils.messages.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/process")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProcessController {

    private final ProcessFindAllUseCase processFindAllUseCase;
    private final ProcessFindByIdUseCase processFindByIdUseCase;
    private final ProcessCreateUseCase processCreateUseCase;
    private final ProcessUpdateUseCase processUpdateUseCase;
    private final ProcessDeleteByIdUseCase processDeleteByIdUseCase;

    private final ProcessCreateMapper processCreateMapper = new ProcessCreateMapper();
    private final ProcessUpdateMapper processUpdateMapper = new ProcessUpdateMapper();
    private final ProcessResponseMapper processResponseMapper = new ProcessResponseMapper();
    private final HttpUtils httpUtils = new HttpUtils();

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProcessResponseDTO>>> findAll() {
        ApiResponse<List<ProcessResponseDTO>> response = new ApiResponse<>();
        try {
            List<ProcessResponseDTO> processes = processResponseMapper.modelsToDtos(processFindAllUseCase.findAll());
            response.setData(processes);
            return ResponseEntity.ok(response);
        } catch (NoResultsException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProcessResponseDTO>> findById(@PathVariable Long id) {
        ApiResponse<ProcessResponseDTO> response = new ApiResponse<>();
        try {
            Process process = processFindByIdUseCase.findById(id);
            response.setData(processResponseMapper.modelToDto(process));
            return ResponseEntity.ok(response);
        } catch (NoResultsException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProcessResponseDTO>> create(@RequestBody ProcessCreateDTO process) {
        ApiResponse<ProcessResponseDTO> response = new ApiResponse<>();
        try {
            response.setData(processResponseMapper.modelToDto(processCreateUseCase.create(processCreateMapper.dtoToModel(process))));
            return ResponseEntity.ok(response);
        } catch (DuplicatedException | InvalidBodyException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse<ProcessResponseDTO>> update(@RequestBody ProcessUpdateDTO process) {
        ApiResponse<ProcessResponseDTO> response = new ApiResponse<>();
        try {
            response.setData(processResponseMapper.modelToDto(processUpdateUseCase.update(processUpdateMapper.dtoToModel(process))));
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
            processDeleteByIdUseCase.deleteById(id);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (NonExistenceException e) {
            response.setError(httpUtils.determineErrorMessage(e));
            return new ResponseEntity<>(response, httpUtils.determineHttpStatus(e));
        }
    }

}
