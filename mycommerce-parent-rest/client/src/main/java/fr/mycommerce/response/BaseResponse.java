package fr.mycommerce.response;

import java.io.Serializable;

import fr.mycommerce.service.ID;

public class BaseResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * statut de retour du service
	 */
	private BaseStatus status;

	/**
	 * messages du service (null si rien à signaler)
	 */
	private String[] messages;

	/**
	 * <h1>identifiant de la resource</h1>
	 * <p>
	 * Renseigné lors d'une réponse 201 sur une demande post pour la création d'une
	 * nouvelle resource.
	 * </p>
	 * <p>
	 * Seulement id est retourné car les autres informations son déjà connues
	 * lors de la demande.
	 * </p>
	 * 
	 */
	private String id;

	/**
	 * les données retournées par le service
	 */
	private ID<?> data;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ID<?> getData() {
		return data;
	}

	public void setData(ID<?> data) {
		this.data = data;
	}

	public BaseStatus getStatus() {
		return status;
	}

	public void setStatus(BaseStatus status) {
		this.status = status;
	}

	public String[] getMessages() {
		return messages;
	}

	public void setMessages(String[] messages) {
		this.messages = messages;
	}

}
