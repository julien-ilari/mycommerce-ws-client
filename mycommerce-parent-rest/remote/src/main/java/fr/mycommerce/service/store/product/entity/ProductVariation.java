package fr.mycommerce.service.store.product.entity;

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
//@Cacheable(false)
@Table(name = "PRODUCT_VARIATION", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class ProductVariation implements ID<Long> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Identifiant techique du produit
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_PRODUCT_VARIATION")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "REFERENCE_SHOP")
	private String referenceShop;

	@Column(name = "REFERENCE_PROVIDER")
	private String referenceProvider;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PICTURE")
	@ToString.Exclude
	private byte[] picture;

	@Column(name = "PRICE_IMPACT")
	private Double priceImpact;

	@Column(name = "QUANTITY")
	private Long quantity;

	@Column(name = "IS_DEFAULT_VARIATION")
	private boolean defaultVariation;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Product.class)
	@JoinColumn(name = "ID_PRODUCT")
	@ToString.Exclude
	private Product product;

}
