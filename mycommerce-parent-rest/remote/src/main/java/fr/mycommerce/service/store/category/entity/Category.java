package fr.mycommerce.service.store.category.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import fr.mycommerce.service.ID;
import fr.mycommerce.service.store.product.entity.ProductCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Réprésentention Objet de l'entité catégorie
 * 
 * @author Julien ILARI
 *
 */
@Data @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Cacheable(false)
@Entity
@Table(name = "CATEGORY", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Category implements ID<Long> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_CATEGORY")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@EqualsAndHashCode.Include
	private Long id;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	@ToString.Exclude
	private List<ProductCategory> products = new ArrayList<>();

	/**
	 * , Catégorie parente referencedColumnName = "id",
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "category_parent_id", nullable = true)
	@ToString.Exclude
	private Category parentCategory;

	/**
	 * Liste des sous-catégories FetchType.LAZY
	 */
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "parentCategory", fetch = FetchType.LAZY, orphanRemoval = true)
	@ToString.Exclude
	@OrderBy("position")
	private Set<Category> childrenCategory = new HashSet<>();

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
	 * position de la catégorie
	 */
	private int position;

	/**
	 * catégorie activé/désactivé
	 */
	private boolean Displayed;

	/**
	 * Image de la catégorie (version non retourchée)
	 */
	@ToString.Exclude
	private byte[] bigPicture;

	/**
	 * Image de la catégorie (version réduite)
	 */
	@ToString.Exclude
	private byte[] smallPicture;

	/**
	 * Les autres images de la catégorie (facultatif)
	 */
	@ToString.Exclude
	private List<byte[]> secondaryPictures;

	/**
	 * Date de création de la catégorie
	 * <p>
	 * La date doit être définie lors de la modification de l'entité
	 * </p>
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	/**
	 * Date de modification de la catégorie
	 * <p>
	 * La date doit être définie lors de la modification de l'entité
	 * </p>
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

}
