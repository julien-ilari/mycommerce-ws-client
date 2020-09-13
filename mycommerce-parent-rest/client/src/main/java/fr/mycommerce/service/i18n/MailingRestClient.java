package fr.mycommerce.service.i18n;

import javax.enterprise.context.Dependent;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import fr.mycommerce.service.ICrudResource;

@Dependent
@Path("/i18n")
@RegisterRestClient(configKey = "mycommerceBaseUri")
public interface MailingRestClient extends ICrudResource<I18nDTO, Long> {

	

}