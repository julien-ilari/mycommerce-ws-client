package fr.mycommerce.service.client;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Dependent
@Path("/customers")
@RegisterRestClient(configKey = "mycommerceBaseUri")
public interface ClientRestClient {

	/**
	 * Récupérer une liste
	 * @return
	 */
	@GET
	//@ClientHeaderParam(name = "Authorization", value = "{lookupAuth}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClientDTO> list();

	/**
	 * Création Bearer pour le "ClientHeaderParam Authorization".
	 * @return
	 */
//	default String lookupAuth()
//	{
//		// Création d'une nouvelle instance sur la configuration présent dans keycloak.json
//		AuthzClient authzClient = AuthzClient.create();
//
//		// CRéation d'une AuthorizationRequest
//		AuthorizationRequest request = new AuthorizationRequest();
//
//		// envoyer la demande de droit au serveur afin d'obtenir un RPT 
//		// avec toutes les autorisations accordées à l'utilisateur
//		AuthorizationResponse response = authzClient.authorization("julien.ilari@gmail.com", "password").authorize(request);
//		
//		return "Bearer " +  response.getToken();
//	}

}