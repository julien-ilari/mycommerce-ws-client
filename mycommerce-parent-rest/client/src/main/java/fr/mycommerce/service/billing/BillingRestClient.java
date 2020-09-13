package fr.mycommerce.service.billing;

import javax.enterprise.context.Dependent;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import fr.mycommerce.service.ICrudResource;

@Dependent
@Path("/invoices")
@RegisterRestClient(configKey = "mycommerceBaseUri")
public interface BillingRestClient extends ICrudResource<BillingDTO, Long> {

	
}