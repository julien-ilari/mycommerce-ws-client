package fr.mycommerce.service.store.product;

import fr.mycommerce.service.ID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductVariationDTO implements ID<Long> {

	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * Référence en boutique
	 */
	private String referenceShop;

	/**
	 * Référence chez le fournisseur
	 */
	private String referenceProvider;

	/**
	 * Nom variation produit
	 */
	private String name;

	/**
	 * Image de representation de la variation produit
	 */
	private byte[] picture;

	/**
	 * Impact sur le prix (HT)
	 */
	private double priceImpact;

	/**
	 * Quantité disponible
	 */
	private int quantity;

	/**
	 * Variation du produit par défaut ?
	 */
	private boolean defaultVariation;

}
