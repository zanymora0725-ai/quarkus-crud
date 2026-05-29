package com.extezany.resource;

import com.extezany.dto.PersonaRequestDTO;
import com.extezany.dto.PersonaResponseDTO;
import com.extezany.service.PersonaService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/personas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonaResource {

    @Inject
    PersonaService service;

    @GET
    public List<PersonaResponseDTO> listar() {
        return service.listar();
    }

    @GET
    @Path("/{id}")
    public PersonaResponseDTO buscarPorId(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    public Response crear(@Valid PersonaRequestDTO dto) {
        PersonaResponseDTO response = service.crear(dto);

        return Response.status(Response.Status.CREATED)
                .entity(response)
                .build();
    }

    @PUT
    @Path("/{id}")
    public PersonaResponseDTO actualizar(
            @PathParam("id") Long id,
            @Valid PersonaRequestDTO dto) {

        return service.actualizar(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        service.eliminar(id);

        return Response.noContent().build();
    }
}