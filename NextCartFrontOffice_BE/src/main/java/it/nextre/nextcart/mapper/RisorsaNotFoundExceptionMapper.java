package it.nextre.nextcart.mapper;

import org.jboss.logging.Logger;
import it.nextre.nextcart.exception.ErrorResponse;
import it.nextre.nextcart.exception.RisorsaNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RisorsaNotFoundExceptionMapper implements ExceptionMapper<RisorsaNotFoundException> {

	private Logger log;
	
	public RisorsaNotFoundExceptionMapper(Logger log) {
		this.log = log;
	}
	
    @Override
    public Response toResponse(RisorsaNotFoundException exception) {
    	
    	log.errorf("Eccezione con stato 404: %s", exception.getMessage());
    	
        return Response.status(Response.Status.NOT_FOUND)
                       .entity(new ErrorResponse("NOT_FOUND", exception.getMessage()))
                       .build();
    }
}