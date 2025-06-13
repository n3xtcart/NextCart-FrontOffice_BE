package it.nextre.nextcart.mapper;

import org.jboss.logging.Logger;
import it.nextre.nextcart.exception.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InternalServerErrorMapper implements ExceptionMapper<Throwable>{
	
	private Logger log;
	
	public InternalServerErrorMapper(Logger log){
		this.log = log;
	}
	
    @Override
    public Response toResponse(Throwable exception) {
    	
    	log.errorf("Eccezione: %s \nCausa: %s", exception.getMessage(), exception.getCause());
    	
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                       .entity(new ErrorResponse("INTERNAL_SERVER_ERROR", "Errore interno"))
                       .build();
    }

}
