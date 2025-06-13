package it.nextre.nextcart.mapper;

import org.jboss.logging.Logger;
import it.nextre.nextcart.exception.ErrorResponse;
import it.nextre.nextcart.exception.ProdottoPresenteException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ProdottoPresenteMapper implements ExceptionMapper<ProdottoPresenteException>{
	
	private Logger log;
	
	public ProdottoPresenteMapper(Logger log){
		this.log = log;
	}
	
    @Override
    public Response toResponse(ProdottoPresenteException exception) {
    	
    	log.errorf("Eccezione con stato 409: %s",exception.getMessage());
    	
        return Response.status(Response.Status.CONFLICT)
                       .entity(new ErrorResponse("CONFLICT", exception.getMessage()))
                       .build();
    }

}
