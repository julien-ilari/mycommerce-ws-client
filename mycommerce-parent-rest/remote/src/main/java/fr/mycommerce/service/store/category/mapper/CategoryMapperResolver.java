package fr.mycommerce.service.store.category.mapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;

import fr.mycommerce.service.store.category.CategoryDTO;
import fr.mycommerce.service.store.category.entity.Category;

@ApplicationScoped
public class CategoryMapperResolver {

	@Inject
	private EntityManager entityManager;

	@ObjectFactory
	public Category resolve(final CategoryDTO dto, @TargetType Class<Category> type) {
		if (dto == null || dto.getId() == null) {
			return new Category();
		} else {
			final StringBuilder querySB = new StringBuilder("SELECT category from CategoryEntity category ");
			querySB.append("left join fetch category.parentCategory ");
			querySB.append("left join fetch category.childrenCategory ");
			querySB.append("where category.id=:id");

			final Query query = entityManager.createQuery(querySB.toString()).setParameter("id", dto.getId());
			
			final Category value = (Category) query.getSingleResult();
			entityManager.refresh(value);

			return value;
		}

	}
}