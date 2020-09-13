package fr.mycommerce.service.store.category;

import fr.mycommerce.service.store.category.entity.Category;
import fr.mycommerce.service.store.category.mapper.CategoryMapper;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.mapping.MappingConfig;

import java.util.List;

/**
 * Dépôt des catégories
 * 
 * @author Julien ILARI
 *
 */
@Repository(forEntity = Category.class) // Entity where c.parentCategory is null
@MappingConfig(CategoryMapper.class) // Mapper
public interface CategoryRepository extends EntityRepository<CategoryDTO, Long> {

	@Query(value = "select c from CategoryEntity c " + "where c.parentCategory is null ", isNative = false)
	@Override
	List<CategoryDTO> findAll();

	default void deleteById(final Long id) {
		final CategoryDTO category = findBy(id);

		// Suppression de l'enfant du parent
		for (CategoryDTO categoryDTO : findAll()) {
			if (categoryDTO.getChildren().contains(category)) {
				categoryDTO.getChildren().remove(category);
				save(categoryDTO);
				return;
			}
		}

		remove(category);
	}

}
