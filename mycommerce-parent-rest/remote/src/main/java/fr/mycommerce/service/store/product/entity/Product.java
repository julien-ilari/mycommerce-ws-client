package fr.mycommerce.service.store.product.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "PRODUCT", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Product implements ID<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_PRODUCT")
	@EqualsAndHashCode.Include
	private Long id;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	@ToString.Exclude
	private List<ProductCategory> categories = new ArrayList<>();

	@Column(name = "REF_PRODUCT")
	private String reference;

	@Column(name = "CODE_CLIENT")
	private String codeClient;

	@Column(name = "CODE_BARRE")
	private String codeBarre;

	@Column(name = "PRICE")
	private Double priceHT;

	@Column(name = "QUANTITY")
	private Long quantity;

	@OneToMany(targetEntity = ProductLang.class, mappedBy = "product", cascade = {
			CascadeType.REMOVE }, orphanRemoval = true)
	@ToString.Exclude
	private List<ProductLang> productLang = new ArrayList<>();

	@OneToMany(targetEntity = ProductVariation.class, mappedBy = "product", cascade = {
			CascadeType.REMOVE }, orphanRemoval = true)
	@ToString.Exclude
	private List<ProductVariation> variations = new ArrayList<>();

	@OneToMany(targetEntity = ProductImage.class, mappedBy = "product", cascade = {
			CascadeType.REMOVE }, orphanRemoval = true)
	@ToString.Exclude
	private List<ProductImage> images = new ArrayList<>();

	@ToString.Exclude
	private byte[] picture;

	private Integer packageWidth;

	private Integer packageHeight;

	private Integer packageDepth;

	private Integer packageWeight;

}
