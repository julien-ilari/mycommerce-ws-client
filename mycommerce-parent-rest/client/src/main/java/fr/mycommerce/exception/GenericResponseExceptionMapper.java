package fr.mycommerce.exception;



import java.net.UnknownServiceException;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

@Provider
public class GenericResponseExceptionMapper implements
    ResponseExceptionMapper<Exception> {

    @Override
    public boolean handles(int statusCode, MultivaluedMap<String, Object> headers) {
        return statusCode == 404  // Not Found
        	|| statusCode == 401
            || statusCode == 409; // Conflict
    }

    @Override
    public Exception toThrowable(Response response) {
        switch(response.getStatus()) {
        case 404: return new UnknownServiceException();
        case 401: return new Exception("application non authorisée");
        case 409: return new Exception();
        }
        return null;
    }

}