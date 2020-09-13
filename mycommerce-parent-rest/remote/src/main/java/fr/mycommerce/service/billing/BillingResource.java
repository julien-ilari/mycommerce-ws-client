package fr.mycommerce.service.billing;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import fr.mycommerce.service.DefaultResource;
import lombok.Getter;

/**
 * Rest API pour la facturation
 * @author Julien ILARI
 *
 */
@RequestScoped
@Path("invoices")
@Tag(name = "Resource Facturations", description = "Resource de gestion des factures") 
public class BillingResource implements DefaultResource<BillingDTO, Long>
{
	@Inject @Getter
    private BillingManager manager;

}