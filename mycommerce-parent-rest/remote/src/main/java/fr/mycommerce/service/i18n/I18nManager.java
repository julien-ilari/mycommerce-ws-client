package fr.mycommerce.service.i18n;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import fr.mycommerce.service.DefaultManager;
import lombok.Getter;

/**
 * Service de gestion des clients
 * @author Julien ILARI
 *
 */
@Dependent
public class I18nManager implements DefaultManager<I18nDTO, Long>
{	
	@Inject @Getter
    private I18nRepository repository;
	

}