package fr.mycommerce.service.store.product;

import javax.enterprise.context.Dependent;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import fr.mycommerce.service.ICrudResource;

@Dependent
@Path("/products")
@RegisterRestClient
public interface ProductRestClient extends ICrudResource<ProductLangDTO, Long>{

	

}