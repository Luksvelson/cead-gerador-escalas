package org.example.resources;

import org.example.domain.Ministerio;
import org.example.services.MinisterioService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/ministerios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MinisteriosResource {
    @Inject
    MinisterioService ministerioService;

    @POST
    public Response createMinisterio(Ministerio ministerio) {
        try {
            ministerioService.createMinisterio(ministerio);
            return Response.status(Response.Status.CREATED).entity(ministerio).build();
        } catch (Exception e) {
            return Response.status(400, e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Ministerio getMinisterio(@PathParam("id") Long id) {
        Ministerio ministerio = ministerioService.findMinisterio(id);
        return ministerio;
    }

    @GET
    public List<Ministerio> getAllMinisterios(){
        return ministerioService.listAllMimnisterios();
    }

    @PUT
    public Response updateMinisterio(Ministerio ministerio) {
        try {
            ministerioService.updateMinisterio(ministerio);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    public Response deleteMinisterio(Long id) {
        try {
            ministerioService.deleteMinisterio(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
