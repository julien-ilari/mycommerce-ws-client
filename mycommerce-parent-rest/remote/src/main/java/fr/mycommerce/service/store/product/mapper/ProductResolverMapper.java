package fr.mycommerce.service.store.product.mapper;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;

import fr.mycommerce.service.store.product.ProductDTO;
import fr.mycommerce.service.store.product.entity.Product;

@ApplicationScoped
public class ProductResolverMapper {
    
    @Inject
    private EntityManager entityManager;

    @ObjectFactory
    public Product resolve(ProductDTO reference, @TargetType Class<Product> entityClass) {    	
    	if(reference == null || reference.getId() == null) { 
    		return new Product();
		}
    	
    
    	return Optional.ofNullable(entityManager.find(entityClass, reference.getId()))
    			.orElse(new Product());
    }


}