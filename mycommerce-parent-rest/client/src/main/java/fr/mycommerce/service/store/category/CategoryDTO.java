package fr.mycommerce.service.store.category;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import fr.mycommerce.service.ID;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Réprésentention Objet de l'entité catégorie
 * 
 * @author Julien ILARI
 *
 */
@Data
//@XmlRootElement(name = "Category")
public class CategoryDTO implements ID<Long>, Comparable<CategoryDTO> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	/**
	 * Invalid characters: <>;=#{}
	 */
	private String name;
	
//	/**
//	 * 
//	 */
//	@EqualsAndHashCode.Exclude
//	//@JsonbTransient
//	private CategoryDTO parent;
	
	/**
	 * Sous Catégories
	 */
	@EqualsAndHashCode.Exclude
	private List<CategoryDTO> children = new ArrayList<>();

	/**
	 * Catégorie a afficher.
	 */
	private boolean diplayed;

	/**
	 * Description détaillée de la catégorie
	 */
	@NotNull
	private String description;

	/**
	 * Désignation simple de la catégorie
	 */
	@NotNull
	private String designation;

	/**
	 * Image principal de la catégorie.
	 * <p>
	 * NB : Utile pour accompagner la présentation de la catégorie avec une image
	 * </p>
	 */
	private byte[] bigPicture;

	/**
	 * Petite image de la catégorie
	 * <p>
	 * NB : Utile pour présenter les sous-catégories dans la catégorie parente
	 * </p>
	 */
	private byte[] subImage;

	/**
	 * Vignette de la catégorie
	 * <p>
	 * NB : Utile pour la présentation dans un menu
	 * </p>
	 */
	private byte[] thumbnail;

	/**
	 * Position d'affichage de la catégorie
	 */
	@NotNull
	private int position;

	/**
	 * la position la plus petite en premier
	 */
	@Override
	public int compareTo(CategoryDTO arg) {
		return this.position - arg.position;
	}

}
