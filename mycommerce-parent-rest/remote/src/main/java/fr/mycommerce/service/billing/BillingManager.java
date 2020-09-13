package fr.mycommerce.service.billing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import fr.mycommerce.service.DefaultManager;
import lombok.Getter;

/**
 * Service de gestion des clients
 * @author Julien ILARI
 *
 */
@ApplicationScoped
public class BillingManager implements DefaultManager<BillingDTO, Long>
{	
	@Inject @Getter
    private BillingRepository repository;
	

}