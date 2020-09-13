package fr.mycommerce.web.view.client;

import java.io.Serializable;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import fr.mycommerce.service.client.ClientRestClient;

@Named
@RequestScoped
@PermitAll
public class ClientMB implements Serializable
{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@RestClient
	private ClientRestClient clientAPI;
	

	public String getClients()
	{
		return clientAPI.list().toString();
	}
	
}
