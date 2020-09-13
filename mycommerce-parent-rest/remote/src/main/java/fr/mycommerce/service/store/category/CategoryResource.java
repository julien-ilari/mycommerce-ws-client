package fr.mycommerce.service.store.category;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import fr.mycommerce.service.DefaultResource;
import lombok.Getter;

@RequestScoped
@Path("categories")
@Tag(name = "Resource Catégorie", description = "Resource de gestion des catogories") 
public class CategoryResource implements DefaultResource<CategoryDTO, Long>
{
	@Inject @Getter
    private CategoryManager manager;

}