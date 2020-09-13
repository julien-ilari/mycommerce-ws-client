package fr.mycommerce.service.store.product.repository;

import java.util.List;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.mapping.MappingConfig;

import fr.mycommerce.service.store.product.ProductLangDTO;
import fr.mycommerce.service.store.product.entity.ProductLang;
import fr.mycommerce.service.store.product.mapper.ProductParentMapper.IProductLangMapper;

/**
 * Dépôt des produits
 * 
 * @author Julien ILARI
 *
 */
@Repository(forEntity = ProductLang.class)
@MappingConfig(IProductLangMapper.class)
public interface ProductLangRepository extends EntityRepository<ProductLangDTO, Long> {

	/**
	 * Recherche des produits en fonction de la langue
	 * 
	 * @param lang langue des produits
	 * @return
	 */
	@Query(value =  "select p from ProductLang p where p.identity.codeLang = ?1")
	List<ProductLangDTO> findAllByLang(String lang);

	/**
	 * Recherche un produit dans une langue précise
	 * 
	 * @param lang langue du produit
	 * @param id   identifiant du produit
	 * @return
	 */
	@Query("select p from ProductLang p where p.identity.codeLang = ?1 and p.identity.idProduct = ?2")
	ProductLangDTO findByLangAndIdProduct(String lang, Long idProduct);
	
	

}