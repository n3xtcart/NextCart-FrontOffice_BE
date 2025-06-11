package it.nextre.nextcart.mapper;

import it.nextre.nextcart.exception.ListaNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ListaNotFoundExceptionMapper implements ExceptionMapper<ListaNotFoundException> {


    @Override
    public Response toResponse(ListaNotFoundException exception) {

        String errorMessage = "La lista con ID " + exception.getListaId() + " non è stata trovata.";

        return Response.status(Response.Status.NOT_FOUND)
                       .entity(errorMessage)
                       .build();
    }
}