package fr.mycommerce.service.mail;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@RequestScoped
@Path("mails")
@Tag(name = "Resource Mailling", description = "Resource d'envoi e-mail") 
public class MailingResource {

	@Inject
	private MailingManager mailingService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response send(@QueryParam("from") String from, @QueryParam("message") String message)
			throws MessagingException {
		MailDTO dto = mailingService.sendAsHtml(from, "demande de contact", message);

		return Response.status(Response.Status.OK).entity(dto).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() throws MessagingException {
		return Response.status(Response.Status.OK).entity(mailingService.list()).build();
	}

}