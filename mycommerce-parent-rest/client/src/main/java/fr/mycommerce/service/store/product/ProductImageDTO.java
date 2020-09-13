package fr.mycommerce.service.store.product;

import fr.mycommerce.service.ID;
import lombok.Data;

/**
 * Image du produit
 * 
 * @author Julien ILARI
 *
 */
@Data
public class ProductImageDTO implements ID<Long> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private byte[] picture;
	private Boolean cover;

}
