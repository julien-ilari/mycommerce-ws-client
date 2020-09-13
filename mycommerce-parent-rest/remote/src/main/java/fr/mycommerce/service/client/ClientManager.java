package fr.mycommerce.service.client;

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
public class ClientManager implements DefaultManager<ClientDTO, Long>
{	
	@Inject @Getter
    private ClientRepository repository;
	

}