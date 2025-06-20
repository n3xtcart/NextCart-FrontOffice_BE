package it.nextre.nextcart.mapper;

import org.jboss.logging.Logger;
import it.nextre.nextcart.exception.ErrorResponse;
import it.nextre.nextcart.exception.TokenConvertException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class TokenConvertMapper implements ExceptionMapper<TokenConvertException> {
	
	
	private Logger log;
	
	public TokenConvertMapper(Logger log){
		this.log = log;
	}
	
    @Override
    public Response toResponse(TokenConvertException exception) {
    	
    	log.errorf("Eccezione 401: %s", exception.getMessage());
    	
        return Response.status(Response.Status.UNAUTHORIZED)
                       .entity(new ErrorResponse("UNAUTHORIZED", "Autenticazione fallita"))
                       .build();
    }

}
