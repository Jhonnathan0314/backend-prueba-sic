package com.prueba.sic.pruebasic.context.process.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessCreateDTO {

    private String filingYear;
    private String processName;
    private String description;

}
