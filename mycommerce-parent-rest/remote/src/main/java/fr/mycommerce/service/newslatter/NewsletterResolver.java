package fr.mycommerce.service.newslatter;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;

@ApplicationScoped
public class NewsletterResolver {
    
    @Inject
    private EntityManager entityManager;

    @ObjectFactory
    public NewslatterEntity resolve(NewslatterDTO reference, @TargetType Class<NewslatterEntity> entityClass) {    	
    	if(reference == null || reference.getId() == null) { 
    		return new NewslatterEntity();
		}  	
    	
    	return Optional.ofNullable(entityManager.find(entityClass, reference.getId()))
    			.orElse(new NewslatterEntity());
    }


}