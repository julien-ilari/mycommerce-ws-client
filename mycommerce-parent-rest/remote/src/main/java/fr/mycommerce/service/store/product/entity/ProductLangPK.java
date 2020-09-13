package fr.mycommerce.service.store.product.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ProductLangPK implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public ProductLangPK() {
		super();
	}

	@Column(name = "ID_PRODUCT", length = 10, updatable = false)
	private Long idProduct;

	@Column(name = "CODE_LANG", length = 10, updatable = false)
	private String codeLang;

}
