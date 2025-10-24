package com.example.jaxrs.controllers;

import com.example.jaxrs.entities.Compte;
import com.example.jaxrs.repositories.CompteRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Path("/banque") // 👈 Important !
public class CompteRestJaxRSAPI {

    @Autowired
    private CompteRepository compteRepository;

    @GET
    @Path("/comptes")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    @GET
    @Path("/comptes/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCompteById(@PathParam("id") Long id) {
        Optional<Compte> compte = compteRepository.findById(id);
        return compte.map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    @Path("/comptes")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Compte createCompte(Compte compte) {
        compte.setId(null);
        return compteRepository.save(compte);
    }

    @PUT
    @Path("/comptes/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateCompte(@PathParam("id") Long id, Compte compte) {
        if (!compteRepository.existsById(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        compte.setId(id);
        return Response.ok(compteRepository.save(compte)).build();
    }

    @DELETE
    @Path("/comptes/{id}")
    public Response deleteCompte(@PathParam("id") Long id) {
        if (!compteRepository.existsById(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        compteRepository.deleteById(id);
        return Response.noContent().build();
    }
}
