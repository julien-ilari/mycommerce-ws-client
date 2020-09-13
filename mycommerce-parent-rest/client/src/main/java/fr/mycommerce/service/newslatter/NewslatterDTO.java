package fr.mycommerce.service.newslatter;

import fr.mycommerce.service.ID;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Réprésentation Request pour le service API "Newslatter"
 * @author Julien ILARI  2020-09
 *
 */
@Data
@NoArgsConstructor
public class NewslatterDTO implements ID<Long>
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	/**
	 * Nom de famille du client
	 */
	private String email;
	
}
