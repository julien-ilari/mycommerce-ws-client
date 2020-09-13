package fr.mycommerce.service.i18n;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.mapping.MappingConfig;

/**
 * Dépôt i18n
 * @author Julien ILARI
 *
 */
@Repository(forEntity = I18nEntity.class) // Entity
@MappingConfig(I18nMapper.class) // Mapper
public interface I18nRepository  extends EntityRepository<I18nDTO, Long> {

}