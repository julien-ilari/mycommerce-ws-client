package fr.mycommerce.service.store.product;

import java.util.ArrayList;
import java.util.List;

import fr.mycommerce.service.ID;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Réprésentention Objet de l'entité produit
 * 
 * @author Julien ILARI
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ProductDTO implements ID<Long> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Référence (technique/fonctionnel) du produit
	 */
	private Long id;
	
	/**
	 * Code client
	 */
	private String codeClient;
	
	/**
	 * Référence unique du produit
	 */
	private String reference;

	/**
	 * Image principal du produit
	 */
	private byte[] picture;

	/**
	 * Statut (actif ou désactivé ?)
	 */
	private boolean status = true;
	
	/**
	 * Référencement - SEO
	 */
	private ProductSeoDTO seo = new ProductSeoDTO();

	/**
	 * Stock
	 */
	private ProductStockDTO stock = new ProductStockDTO();

	/**
	 * Livraison
	 */
	private ProductShippingDTO shipping = new ProductShippingDTO();

	/**
	 * Prix
	 */
	private ProductPricingDTO pricing = new ProductPricingDTO();


	/**
	 * Image(s)
	 */
	private List<ProductImageDTO> images = new ArrayList<>();

	/*
	 * ================================================================
	 * =========================== Options =============================
	 * ================================================================
	 */

}