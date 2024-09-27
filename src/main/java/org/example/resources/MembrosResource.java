package org.example.resources;

import org.example.domain.Membro;
import org.example.services.MembroService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/membros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MembrosResource {

    @Inject
    MembroService membroService;

    @POST
    public Response createMembro(Membro membro) {
        try {
            membroService.createMembro(membro);
            return Response.status(Response.Status.CREATED).entity(membro).build();
        } catch (Exception e) {
            return Response.status(400, e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Membro getMembro(@PathParam("id") Long id) {
        return membroService.findMembro(id);
    }

    @GET
    public List<Membro> getAllMembros(){
        return membroService.listAllMemmbros();
    }

    @PUT
    public Response updateMembro(Membro membro) {
        try {
            membroService.updateMembro(membro);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    public Response deleteMembro(Long id) {
        try {
            membroService.deleteMembro(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
