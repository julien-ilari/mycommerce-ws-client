package fr.mycommerce.web.view.contact;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import fr.mycommerce.service.mail.MailingResponse;
import fr.mycommerce.service.mail.MailingRestClient;
import lombok.Getter;

@Named
@RequestScoped
public class ContactMB implements Serializable
{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@RestClient
	private MailingRestClient restClient;
	
	@Getter
	private List<MailingResponse> values;
	
	@Inject @Getter
	private ContactModel model;
	
	@PostConstruct
	public void onload() {
		values =  restClient.findAll();
	}
	
	public void submit(AjaxBehaviorEvent event) 
	{
		values.add(restClient.send(model.getEmail(), model.getMessage()));
	}
	
}
