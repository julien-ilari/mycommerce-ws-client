package fr.mycommerce.service.address;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import fr.mycommerce.exception.GenericResponseExceptionMapper;

/**
 * Interface RegisterRestClient correspondant au service distant "delivery"
 * @author Julien ILARI
 *
 */
@Dependent
@Path("/adresses")
@RegisterRestClient(configKey = "mycommerceBaseUri")
@RegisterProvider(GenericResponseExceptionMapper.class)
public interface AddressRestClient {
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	public List<AddressResponse> control(@QueryParam(value = "address") String address);

}