package com.extezany.mapper;

import com.extezany.dto.PersonaRequestDTO;
import com.extezany.dto.PersonaResponseDTO;
import com.extezany.entity.Persona;

public class PersonaMapper {

    public static PersonaResponseDTO toDTO(Persona persona) {

        PersonaResponseDTO dto = new PersonaResponseDTO();

        dto.id = persona.id;
        dto.nombre = persona.nombre;
        dto.apellido = persona.apellido;
        dto.edad = persona.edad;

        return dto;
    }

    public static Persona toEntity(PersonaRequestDTO dto) {

        Persona persona = new Persona();

        persona.nombre = dto.nombre;
        persona.apellido = dto.apellido;
        persona.edad = dto.edad;

        return persona;
    }
}