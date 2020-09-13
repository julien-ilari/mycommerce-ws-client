package fr.mycommerce.service.newslatter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.mapping.MappingConfig;

import fr.mycommerce.service.DefaultManager;
import fr.mycommerce.service.newslatter.NewsletterMapper.INewsletterMapper;
import lombok.Getter;

/**
 * Service de gestion des clients
 * @author Julien ILARI
 *
 */
@ApplicationScoped
public class NewslatterManager implements DefaultManager<NewslatterDTO, Long>
{	
	
	@Repository(forEntity = NewslatterEntity.class)
	@MappingConfig(INewsletterMapper.class)
	public interface NewslatterRepository  extends EntityRepository<NewslatterDTO, Long> {

	}
	
	@Inject @Getter
    private NewslatterRepository repository;
	

}