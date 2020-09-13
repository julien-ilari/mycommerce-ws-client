package fr.mycommerce.service.mail;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Dependent
@Path("/mails")
@RegisterRestClient(configKey = "mycommerceBaseUri")
public interface MailingRestClient {

	@POST
	MailingResponse send(@QueryParam("from") String from, @QueryParam("message") String message);
	
	@GET
	List<MailingResponse> findAll();

}