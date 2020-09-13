package fr.mycommerce.service.store.product.resource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import fr.mycommerce.service.store.product.ProductLangDTO;
import fr.mycommerce.service.store.product.manager.ProductManager;
import lombok.Getter;

@RequestScoped
@Path("products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Resource Produits", description = "Resource de gestion des produits")
public class ProductResource {

	@Inject
	@Getter
	private ProductManager manager;

	@POST
	public Response postProductLang(@HeaderParam(HttpHeaders.CONTENT_LANGUAGE) String contentLanguage,
			ProductLangDTO entity) {
		entity.setCodeLang("FR");

		manager.create(entity);
		return Response.created(UriBuilder.fromResource(this.getClass()).build()).build();
	}

	@GET
	@Path("{lang}/{id}")
	public Response getProductByLangAndId(@PathParam("lang") String lang, @PathParam("id") Long id) {
		return Response.ok(manager.read(lang, id)).build();
	}

	@GET
	@Path("{lang}")
	public Response getProductByLang(@PathParam("lang") String lang) {
		return Response.ok(manager.list(lang)).build();
	}

	@GET
	public Response getProductLangAll() {
		return Response.ok(getManager().list()).build();
	}

	@PUT
	public Response putProductLang(ProductLangDTO entity) {
		entity.setCodeLang("FR");
		return Response.created(
				UriBuilder.fromResource(this.getClass()).path(getManager().create(entity).getId().toString()).build())
				.build();
	}

	@GET
	@Path("{id}")
	public Response getProductById(@PathParam("id") Long id) {
		return Response.ok(getManager().read("FR", id)).build();
	}

	@DELETE
	@Path("{id}")
	@Operation(summary = "Supprimer le produit", description = "Supprimer le produit avec toutes les traductions disponibles.", operationId = "deleteProductById")
	public void deleteProductById(@PathParam("id") Long id) {
		getManager().deleteById(id);
	}

}