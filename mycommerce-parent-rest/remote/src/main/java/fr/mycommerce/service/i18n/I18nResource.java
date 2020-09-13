package fr.mycommerce.service.i18n;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

import fr.mycommerce.service.DefaultResource;
import lombok.Getter;

/**
 * Rest API internationalisation
 * 
 * @author julien ILARI
 *
 */
@RequestScoped
@Path("i18n")
public class I18nResource implements DefaultResource<I18nDTO, Long> {

	@Inject
	@Getter
	private I18nManager manager;

}