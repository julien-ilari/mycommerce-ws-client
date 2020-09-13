package fr.mycommerce.service.billing;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.mapping.MappingConfig;

/**
 * Dépôt des factures
 * @author Julien ILARI
 *
 */
@Repository(forEntity = BillingEntity.class) // Entity
@MappingConfig(BillingMapper.class) // Mapper
public interface BillingRepository  extends EntityRepository<BillingDTO, Long> {

}
