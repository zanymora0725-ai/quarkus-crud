package com.extezany.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class PersonaRequestDTO {

    @NotBlank
    public String nombre;

    @NotBlank
    public String apellido;

    @Min(1)
    public int edad;
}