package fr.mycommerce.web.view.address;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.WebApplicationException;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import fr.mycommerce.service.address.AddressResponse;
import fr.mycommerce.service.address.AddressRestClient;

@Named
@RequestScoped
public class ControlAddressMB {

	@Inject
	@RestClient
	private AddressRestClient deliveryAddressAPI;
	
	private List<AddressResponse> values;
	
	
	public void onload() {
		try {
			values =  deliveryAddressAPI.control("2 avenue de la libération,Gardanne 13120");
		} catch (WebApplicationException e) {
			info("UNe erreur est survenue, veullez raysyer  ",e.getMessage());
			values =  new ArrayList<>();
		}
	}

	public List<AddressResponse> getAddress() {
		return values;
	}

	public void info(final String sumary, final String detail) {
		FacesContext.getCurrentInstance().addMessage("messages",
				new FacesMessage(FacesMessage.SEVERITY_INFO, sumary, detail));
	}

	public void warn(final String sumary, final String detail) {
		FacesContext.getCurrentInstance().addMessage("messages",
				new FacesMessage(FacesMessage.SEVERITY_WARN, sumary, detail));
	}

	public void error(final String sumary, final String detail) {
		FacesContext.getCurrentInstance().addMessage("messages",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, sumary, detail));
	}

	public void fatal(final String sumary, final String detail) {
		FacesContext.getCurrentInstance().addMessage("messages",
				new FacesMessage(FacesMessage.SEVERITY_FATAL, sumary, detail));
	}
}
