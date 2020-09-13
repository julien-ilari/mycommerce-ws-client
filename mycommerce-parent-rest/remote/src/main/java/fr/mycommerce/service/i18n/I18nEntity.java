package fr.mycommerce.service.i18n;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.mycommerce.service.ID;
import lombok.Data;

@Data
@Entity
@Table(name = "I18N")
public class I18nEntity implements ID<Long> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Id @NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String key;

	@NotNull
	private String lang;
	
	@NotNull
	private String value;

	
}
