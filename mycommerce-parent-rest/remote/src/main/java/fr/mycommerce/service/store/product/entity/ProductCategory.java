package fr.mycommerce.service.store.product.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import fr.mycommerce.service.store.category.entity.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Réprésentention Objet de l'entité produit
 * 
 * @author Julien ILARI
 *
 */
@Data
@Entity
@Table(name = "PRODUCT_CATEGORY")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductCategory implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	@EqualsAndHashCode.Include
	private ProductCategoryPK id;

	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "ID_PRODUCT")
	private Product product;

	@ManyToOne
	@MapsId("categoryId")
	@JoinColumn(name = "ID_CATEGORY")
	private Category category;


}
