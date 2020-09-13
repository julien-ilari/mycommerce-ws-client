package fr.mycommerce.service.client;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import fr.mycommerce.service.ID;
import fr.mycommerce.service.address.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Réprésentation objet des données du client
 * 
 * @author Julien ILARI 2020-09
 *
 */
@Data
@Entity @Table(name = "CLIENT")
@NoArgsConstructor
public class ClientEntity implements ID<Long> {
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
	@Schema(required = true)
	@Column(name = "family_name", length = 50)
	private String nom;

	/**
	 * Prénom du client
	 */
	@Schema(required = true)
	@Column(name = "first_name", length = 50)
	private String prenom;
	
	@OneToOne
    private Address address;

	/**
	 * Informations complémentaire sur le client
	 */
	@Schema(required = true)
	@Column(name = "INFO", length = 512)
	private String info;


}
