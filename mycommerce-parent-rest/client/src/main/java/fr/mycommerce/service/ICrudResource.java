package fr.mycommerce.service;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;

/**
 * Interface APPLICATION_JSON CRUD (Create Read Update Delete)
 * 
 * @author Julien ILARI 2020-09
 *
 * @param <B> type bean de transfert
 * @param <I> type identifiant du bean
 */
public interface ICrudResource<B extends Serializable, I extends Serializable> {

	/**
	 * Demander la liste des éléments
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ClientHeaderParam(name = HttpHeaders.CONTENT_LANGUAGE, value = "{getLanguage}")
	public List<B> get();
	
	@GET @Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ClientHeaderParam(name = HttpHeaders.CONTENT_LANGUAGE, value = "{getLanguage}")
	public B getById(@PathParam("id") I identifiant);

	/**
	 * Demander l'ajout d'un nouveau élément
	 * 
	 * @param value élément à ajouter
	 * @return élément créé
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ClientHeaderParam(name = HttpHeaders.CONTENT_LANGUAGE, value = "{getLanguage}")
//	@ClientHeaderParam(name=HttpHeaders.CONTENT_LANGUAGE, value="{getLanguage}")
//	@ClientHeaderParam(name=HttpHeaders.CONTENT_TYPE, value="text/html; charset=utf-8")
	public void post(B value);

	/**
	 * Mise à jour d'un élément existant
	 * 
	 * @param value élément à mettre à jour
	 * @return
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
//	@ClientHeaderParam(name=HttpHeaders.CONTENT_LANGUAGE, value="{getLanguage}")
//	@ClientHeaderParam(name=HttpHeaders.CONTENT_TYPE, value="text/html; charset=utf-8")
	public void put(B value);

	/**
	 * Suppression d'un élément existant
	 * 
	 * @param identifiant de l'élément à supprimer.
	 */
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") I identifiant);

	default String getLanguage() {
		final Locale l = FacesContext.getCurrentInstance().getViewRoot().getLocale();

		return l.toLanguageTag();
	}

}