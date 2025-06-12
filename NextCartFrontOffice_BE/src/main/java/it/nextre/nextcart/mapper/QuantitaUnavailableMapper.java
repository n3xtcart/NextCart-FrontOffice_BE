package it.nextre.nextcart.mapper;

import org.jboss.logging.Logger;
import it.nextre.nextcart.exception.ErrorResponse;
import it.nextre.nextcart.exception.QuantitaUnavailableException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class QuantitaUnavailableMapper implements ExceptionMapper<QuantitaUnavailableException> {

	private Logger log;
	
	public QuantitaUnavailableMapper(Logger log) {
		this.log = log;
	}
	
    @Override
    public Response toResponse(QuantitaUnavailableException exception) {
    	
    	log.error("Eccezione con stato 409: " + exception.getMessage());
    	
        return Response.status(Response.Status.CONFLICT)
                       .entity(new ErrorResponse("CONFLICT", exception.getMessage()))
                       .build();
    }
}