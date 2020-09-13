package fr.mycommerce.service.client;

import javax.enterprise.context.ApplicationScoped;

import fr.mycommerce.service.AbstractMapper;

@ApplicationScoped
public class ClientDtoMapper extends AbstractMapper<ClientEntity, ClientDTO> {

	public ClientDtoMapper() {
		super();
		propMap.put("address", "");
		propIgnore.add("address");
		
	}



}