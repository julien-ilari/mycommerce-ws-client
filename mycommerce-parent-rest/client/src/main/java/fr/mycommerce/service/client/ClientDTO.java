package fr.mycommerce.service.client;

import javax.validation.constraints.NotNull;

import fr.mycommerce.service.ID;

/**
 * Réprésentation objet des données du client
 * 
 * @author Julien ILARI 2020-09
 *
 */
public class ClientDTO implements ID<Long> {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identifiant unique du client
	 */
	private Long id;

	/**
	 * Nom de famille du client
	 */
	@NotNull
	private String nom;

	/**
	 * Prénom du client
	 */
	@NotNull
	private String prenom;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}
