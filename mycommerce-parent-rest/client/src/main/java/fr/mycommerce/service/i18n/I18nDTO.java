package fr.mycommerce.service.i18n;

import fr.mycommerce.service.ID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class I18nDTO implements ID<Long> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String key;

	private String lang;
	
	private String value;

}
