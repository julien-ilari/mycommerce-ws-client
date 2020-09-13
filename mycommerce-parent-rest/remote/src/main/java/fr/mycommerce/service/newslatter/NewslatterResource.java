package fr.mycommerce.service.newslatter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import fr.mycommerce.service.DefaultResource;
import lombok.Getter;

@RequestScoped
@Path("newslatter")
@Tag(name = "Resource Newslatter", description = "Resource gestion des abonnements newslatter") 
public class NewslatterResource implements DefaultResource<NewslatterDTO, Long>
{
	@Inject @Getter
    private NewslatterManager manager;

}