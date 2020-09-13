package fr.mycommerce.service.store.product.entity;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import fr.mycommerce.service.ID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Réprésentention Objet de l'entité produit
 * 
 * @author Julien ILARI
 *
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Cacheable(false)
@Table(name = "PRODUCT_IMAGE", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class ProductImage implements ID<Long> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Identifiant techique
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_PRODUCT_IMAGE")
	@EqualsAndHashCode.Include
	private Long id;

	/**
	 * Produit de l'iimage
	 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Product.class)
	@JoinColumn(name = "ID_PRODUCT")
	@ToString.Exclude
	private Product product;

	/**
	 * Titre de l'image
	 */
	@Column(name = "TITLE")
	private String title;

	/**
	 * Image
	 */
	@Column(name = "PICTURE")
	@ToString.Exclude
	private byte[] picture;
	
	/**
	 * Image de couverture du produit ?
	 */
	@Column(name = "COVER")
	private Boolean cover;

}
