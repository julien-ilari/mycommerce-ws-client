package fr.mycommerce.service.client;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import fr.mycommerce.service.DefaultResource;
import lombok.Getter;

@RequestScoped
@Path("customers")
@Tag(name = "Resource Clients", description = "Resource de gestion des clients") 
public class ClientResource implements DefaultResource<ClientDTO, Long>
{

    @Inject @Getter
    private ClientManager manager;

}
