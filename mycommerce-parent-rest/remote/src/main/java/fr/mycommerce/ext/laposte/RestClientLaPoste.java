package fr.mycommerce.ext.laposte;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import fr.mycommerce.exception.UnknownUriException;
import fr.mycommerce.exception.UnknownUriExceptionMapper;
import fr.mycommerce.service.address.AddressResponse;

/**
 * <h1>Rest Client "ControlAdresse" de "laposte.fr"</h1>
 * <p>
 * Pour plus de détail sur API controlAdresse de La Poste
 * {@link}https://developer.laposte.fr/products/controladresse/latest
 * </p>
 * 
 * @author julien ILARI
 *
 */
@Dependent
@Path("/adresses")
@RegisterProvider(UnknownUriExceptionMapper.class)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "laposteBaseUri", baseUri = "http://api.laposte.fr/controladresse/v1")
public interface RestClientLaPoste extends AutoCloseable {

	@GET
	@ClientHeaderParam(name = "X-Okapi-Key", value = "{getKeyApi}")
	public List<AddressResponse> find(@QueryParam("q") String address) throws UnknownUriException;

	/**
	 * Fourni la clé api pour l'utilisation des services de "laposte.fr"
	 * @return
	 */
	default String getKeyApi() {
		return "LwfnPPEz6+t3w3QWfKNNulVyHRplG1b3tYNOLPnCD6PSlTur9j/8ljZb6xAZ0nz+";
	}

}
