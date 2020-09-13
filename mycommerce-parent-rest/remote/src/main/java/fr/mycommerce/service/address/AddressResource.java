package fr.mycommerce.service.address;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import fr.mycommerce.exception.UnknownUriException;

@ApplicationScoped
@Path("/adresses")
@Tag(name = "Resource Adresses", description = "Resource de gestion des adresses de livraison") 
public class AddressResource {

	@Inject
	private AddressManager manager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list() throws UnknownUriException {
		
		final List<AddressResponse> jsonString = manager.control("2 libération Gardanne");
		
		return Response
			      .status(Response.Status.OK)
			      .entity(jsonString)
			      .build();
	}
	
}
