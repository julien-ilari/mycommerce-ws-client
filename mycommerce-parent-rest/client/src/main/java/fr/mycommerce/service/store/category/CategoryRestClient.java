package fr.mycommerce.service.store.category;

import javax.enterprise.context.Dependent;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import fr.mycommerce.service.ICrudResource;

@Dependent
@Path("/categories")
@RegisterRestClient
public interface CategoryRestClient extends ICrudResource<CategoryDTO, Long>{

	

}