package fr.mycommerce.service.billing;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import fr.mycommerce.service.ID;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Réprésentation pour le service API "Billing"
 * @author Julien ILARI  2020-09
 *
 */
@Data
@Entity 
@Table(name = "BILLING")
@NoArgsConstructor
public class BillingEntity implements ID<Long>
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
	@Schema(required=true)
	private String email;
	
	
}
