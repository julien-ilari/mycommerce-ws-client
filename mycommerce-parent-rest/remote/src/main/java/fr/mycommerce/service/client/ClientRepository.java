package fr.mycommerce.service.client;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.mapping.MappingConfig;

/**
 * Dépôt des clients
 * @author Julien ILARI
 *
 */
@Repository(forEntity = ClientEntity.class) // Entity
@MappingConfig(ClientDtoMapper.class) // Mapper
public interface ClientRepository  extends EntityRepository<ClientDTO, Long> {

}