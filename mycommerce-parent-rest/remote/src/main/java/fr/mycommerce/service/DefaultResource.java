package fr.mycommerce.service;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface DefaultResource<E extends ID<PK>, PK extends Serializable> {

	/**
	 * Service business
	 * 
	 * @return manager
	 */
	public DefaultManager<E, PK> getManager();

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Liste récupérée avec succès", content = @Content(mediaType = "application/json")),
			@APIResponse(responseCode = "401", description = "Vous n'êtes pas autorisé à afficher la ressource"),
			@APIResponse(responseCode = "403", description = "L'accès à la ressource que vous tentiez d'atteindre est interdit"),
			@APIResponse(responseCode = "404", description = "La ressource que vous tentiez d'atteindre est introuvable", content = @Content(mediaType = "application/json")) })
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Rechercher toutes les occurences existantes", description = "Retourne la liste de toutes les occurences.", operationId = "get")
	public default Response get() {
		return Response.ok(getManager().list()).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Création succès", content = @Content(mediaType = "application/json")),
			@APIResponse(responseCode = "401", description = "Vous n'êtes pas autorisé à utiliser la ressource"),
			@APIResponse(responseCode = "403", description = "L'accès à la ressource que vous tentiez d'atteindre est interdit"),
			@APIResponse(responseCode = "404", description = "La ressource que vous tentiez d'atteindre est introuvable", content = @Content(mediaType = "application/json")) })
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "AJouter une ouvelle occurence", description = "AJoute une nouvelle occurance non existante à la liste de toutes les occurences.", operationId = "post")
	public default Response post(E entity) {

		return Response.created(
				UriBuilder.fromResource(this.getClass()).path(getManager().create(entity).getId().toString()).build())
				.build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Création succès", content = @Content(mediaType = "application/json")),
			@APIResponse(responseCode = "401", description = "Vous n'êtes pas autorisé à utiliser la ressource"),
			@APIResponse(responseCode = "403", description = "L'accès à la ressource que vous tentiez d'atteindre est interdit"),
			@APIResponse(responseCode = "404", description = "La ressource que vous tentiez d'atteindre est introuvable", content = @Content(mediaType = "application/json")) })
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "AJouter une ouvelle occurence", description = "AJoute une nouvelle occurance non existante à la liste de toutes les occurences.", operationId = "put")
	public default Response put(E entity) {

		return Response.created(
				UriBuilder.fromResource(this.getClass()).path(getManager().create(entity).getId().toString()).build())
				.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	@Operation(summary = "Rechercher une occurence", description = "Recherche une occurance existante à la liste de toutes les occurences.", operationId = "getById")
	public default Response getById(@PathParam("id") PK id) {
		return Response.ok(getManager().read(id)).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "401", description = "Vous n'êtes pas autorisé à utiliser la ressource"),
			@APIResponse(responseCode = "403", description = "L'accès à la ressource que vous tentiez d'atteindre est interdit"),
			@APIResponse(responseCode = "204", description = "Ressource supprimée avec succès mais pas d’information à renvoyer.") })
	@DELETE
	@Path("{id}")
	@Operation(summary = "Supprimer une occurence", description = "Supprimer une occurance existante à la liste de toutes les occurences.", operationId = "deleteById")
	public default void deleteById(@PathParam("id") PK id) {
		getManager().deleteById(id);
	}

}