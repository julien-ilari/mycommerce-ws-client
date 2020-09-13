package fr.mycommerce.service.address;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @author Julien ILARI
 *
 */
@Data
public class AddressResponse  implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String code;
	private String adresse;
	
}
