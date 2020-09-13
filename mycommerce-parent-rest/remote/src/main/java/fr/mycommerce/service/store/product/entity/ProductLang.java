package fr.mycommerce.service.store.product.entity;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

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
@Table(name = "PRODUCT_LANG")
public class ProductLang implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@EqualsAndHashCode.Include
	private ProductLangPK identity;

	@MapsId("idProduct")
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = Product.class)
	@JoinColumn(name = "ID_PRODUCT")
	@ToString.Exclude
	private Product product;

	/**
	 * Nom du produit
	 */
	@Column(name = "NAME", length = 128)
	private String name;

	/**
	 * Description détaillée
	 */
	@Column(name = "DESCRIPTION", length = 9999)
	private String description;

	/**
	 * Description courte
	 */
	@Column(name = "DESCRIPTION_SHORT", length = 255)
	private String descriptionShort;

	/*
	 * ###################################################################
	 * REFERENCEMENT
	 * ###################################################################
	 */

	/**
	 * 0 of 70 characters used (recommended)
	 */
	@Column(name = "META_TITLE", length = 128)
	private String metaTitle;

	@Column(name = "META_KEYWORDS", length = 255)
	private String metaKeywords;

	/**
	 * 0 of 160 characters used (recommended)
	 */
	@Column(name = "META_DESCRIPTION", length = 255)
	private String metaDescription;

	/**
	 * Récriture de url
	 */
	@Column(name = "FRIENDLY_URL", length = 128)
	private String friendlyURL;

}
