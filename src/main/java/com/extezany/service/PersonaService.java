package com.extezany.service;

import com.extezany.dto.PersonaRequestDTO;
import com.extezany.dto.PersonaResponseDTO;
import com.extezany.entity.Persona;
import com.extezany.exception.ResourceNotFoundException;
import com.extezany.mapper.PersonaMapper;
import com.extezany.repository.PersonaRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PersonaService {

    @Inject
    PersonaRepository personaRepository;

    public List<PersonaResponseDTO> listar() {

        return personaRepository.listAll()
                .stream()
                .map(PersonaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonaResponseDTO buscarPorId(Long id) {

        Persona persona = personaRepository.findById(id);

        if (persona == null) {
            throw new ResourceNotFoundException(
                    "Persona no encontrada con id: " + id);
        }

        return PersonaMapper.toDTO(persona);
    }

    @Transactional
    public PersonaResponseDTO crear(PersonaRequestDTO dto) {

        Persona persona = PersonaMapper.toEntity(dto);

        personaRepository.persist(persona);

        return PersonaMapper.toDTO(persona);
    }

    @Transactional
    public PersonaResponseDTO actualizar(Long id,
                                         PersonaRequestDTO dto) {

        Persona persona = personaRepository.findById(id);

        if (persona == null) {
            throw new ResourceNotFoundException(
                    "Persona no encontrada con id: " + id);
        }

        persona.nombre = dto.nombre;
        persona.apellido = dto.apellido;
        persona.edad = dto.edad;

        return PersonaMapper.toDTO(persona);
    }

    @Transactional
    public void eliminar(Long id) {

        Persona persona = personaRepository.findById(id);

        if (persona == null) {
            throw new ResourceNotFoundException(
                    "Persona no encontrada con id: " + id);
        }

        personaRepository.delete(persona);
    }
}