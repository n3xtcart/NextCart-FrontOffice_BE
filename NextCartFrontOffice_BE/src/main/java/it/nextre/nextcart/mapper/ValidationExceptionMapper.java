package it.nextre.nextcart.mapper;

import org.jboss.logging.Logger;
import it.nextre.nextcart.exception.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException>{
	
	private Logger log;
	
	public ValidationExceptionMapper(Logger log){
		this.log = log;
	}

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		
    	log.errorf("Eccezione con stato 400: %s", exception.getMessage());
    	
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity(new ErrorResponse("BAD_REQUEST", "Errore nella richiesta"))
                       .build();
	}

}
