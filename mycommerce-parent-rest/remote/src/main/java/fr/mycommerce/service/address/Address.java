package fr.mycommerce.service.address;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.mycommerce.service.ID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity @Table(name = "ADDRESS")
@NoArgsConstructor
public class Address implements ID<Long>  {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String address;
	
	

}
