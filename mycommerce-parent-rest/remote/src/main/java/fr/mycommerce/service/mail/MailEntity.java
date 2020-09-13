package fr.mycommerce.service.mail;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import fr.mycommerce.service.ID;
import lombok.Data;

@Data
@Entity
@Table(name = "EMAIL")
public class MailEntity implements ID<Long> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Id @NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String transmitter;

	
	private String sujet;

	private String objet;

	@NotNull
	private String message;


	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateSending;

}
