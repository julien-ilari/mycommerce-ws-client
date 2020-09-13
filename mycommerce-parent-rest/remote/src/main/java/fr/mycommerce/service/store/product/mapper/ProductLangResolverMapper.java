package fr.mycommerce.service.store.product.mapper;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;

import fr.mycommerce.service.store.product.ProductLangDTO;
import fr.mycommerce.service.store.product.entity.ProductLang;
import fr.mycommerce.service.store.product.entity.ProductLangPK;

@ApplicationScoped
public class ProductLangResolverMapper {
    
    @Inject
    private EntityManager entityManager;

    @ObjectFactory
    public ProductLang resolve(ProductLangDTO reference, @TargetType Class<ProductLang> entityClass) {    	
    	if(reference == null || reference.getId() == null || reference.getCodeLang() == null) { 
    		return new ProductLang();
		}
    	
    	final ProductLangPK identity = new ProductLangPK();
    	identity.setIdProduct(reference.getId());
    	identity.setCodeLang(reference.getCodeLang());
    	
    	
    	return Optional.ofNullable(entityManager.find(entityClass, identity))
    			.orElse(new ProductLang());
    }


}