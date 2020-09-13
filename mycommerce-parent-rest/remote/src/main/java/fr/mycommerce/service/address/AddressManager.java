package fr.mycommerce.service.address;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import fr.mycommerce.exception.UnknownUriException;
import fr.mycommerce.ext.laposte.RestClientLaPoste;

@ApplicationScoped
public class AddressManager {

	@Inject
	@RestClient
	private RestClientLaPoste serviceExt;

	/**
	 * Contrôlez et redressez une adresse.
	 * @param address adresse à redresser
	 * @return liste des adresses pour redressement
	 */
	public List<AddressResponse> control(final String address) {
		try {
			return Optional.ofNullable(serviceExt.find(address)).orElse(new ArrayList<>());
		} catch (UnknownUriException e) {
			
			return new ArrayList<>();
		}
	}

}