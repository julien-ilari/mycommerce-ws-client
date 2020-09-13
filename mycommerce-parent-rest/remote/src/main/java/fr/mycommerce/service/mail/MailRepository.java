package fr.mycommerce.service.mail;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.mapping.MappingConfig;

/**
 * Dépôt des mails
 * @author Julien ILARI
 *
 */
@Repository(forEntity = MailEntity.class) // Entity
@MappingConfig(MailMapper.class) // Mapper
public interface MailRepository  extends EntityRepository<MailDTO, Long> {

}
