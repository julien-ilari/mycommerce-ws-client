package fr.mycommerce.service.store.product;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Dependent
@Path("/products/{productId}/variations")
@RegisterRestClient 
public interface ProductVariationRestClient {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductVariationDTO> get(@PathParam("productId") Long productId);
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void post(@PathParam("productId") Long productId, ProductVariationDTO variation);
	
	// : .+
	@DELETE @Path("{ids}")
	public void delete(@PathParam("productId") Long productId, @PathParam("ids") List<Long> ids);

}


