package fr.mycommerce.service.billing;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.mycommerce.service.ID;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Réprésentation Request pour le service API "Newslatter"
 * @author Julien ILARI  2020-09
 *
 */
@Data
@Entity 
@Table(name = "NEWSLATTER")
@NoArgsConstructor
public class BillingDTO implements ID<Long>
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Nom de famille du client
	 */
	@NotNull
	private String email;
	
	
}
