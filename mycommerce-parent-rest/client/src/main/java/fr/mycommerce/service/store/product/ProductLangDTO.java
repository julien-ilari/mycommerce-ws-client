package fr.mycommerce.service.store.product;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Produit dans une langue (exemple FR pour france)
 * 
 * @author Julien ILARI
 *
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductLangDTO extends ProductDTO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * code de la langue
	 */
	private String codeLang;

	/**
	 * Nom du produit traduit
	 */
	private String name;

	/**
	 * Description du produit traduit
	 */
	private String description;

	/**
	 * Description courte du produit traduit
	 */
	private String descriptionShort;


}
