package fr.mycommerce.service.store.product.resource;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import fr.mycommerce.service.store.product.ProductVariationDTO;
import fr.mycommerce.service.store.product.manager.ProductVariationManager;

@RequestScoped
@Path("products/{productId}/variations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Resource Variations Produits", description = "Resource de gestion des variations produits")
public class productVariationResource {

	@Inject
	private ProductVariationManager manager;

	@GET
	@Operation(summary = "Rechercher variations du produit", description = "Recherche les variations du produit.", operationId = "getVariationAll")
	public Response getVariationAll(@PathParam("productId") Long productId) {
		return Response.ok(manager.list(productId)).build();
	}
	
	@POST
	@Operation(summary = "Ajouter variation produit", description = "Demande l'ajout d'une nouvelle variation du produit .", operationId = "postVariation")
	public Response postVariation(@PathParam("productId") Long productId, ProductVariationDTO variation) {
		manager.create(productId, variation);
		return Response.ok().build();
	}
	
	@DELETE @Path("{ids}")
	@Operation(summary = "Supprimer variation produit", description = "Demande la suppression de la variation du produit .", operationId = "deleteVariations")
	public Response deleteVariations(@PathParam("productId") Long productId, @PathParam("ids") List<Long> ids) {
		manager.delete(productId, ids);
		return Response.ok().build();
	}

}