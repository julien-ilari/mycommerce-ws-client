package fr.mycommerce.view.catalog.product;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * ManagedBean pour l'administration des images du produit en-cours d'édition
 * @author Julien ILARI
 *
 */
public class AdminProductImageMB implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Liste des images du produit en-cours d'édition
	 */
	@Getter
	@Setter
	private List<String> images;
	
	@Getter
	@Setter
	private String selectedImage;
	

	
	

}
