package fr.mycommerce.service.store.category.mapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.deltaspike.data.api.mapping.SimpleQueryInOutMapperBase;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import fr.mycommerce.service.store.category.CategoryDTO;
import fr.mycommerce.service.store.category.CategoryRepository;
import fr.mycommerce.service.store.category.entity.Category;
import lombok.Getter;

/**
 * Mapper personnalisé des catégories
 * 
 * @author Julien ILARI
 *
 */
@ApplicationScoped
public class CategoryMapper extends SimpleQueryInOutMapperBase<Category, CategoryDTO> {

	@Inject
	@Getter
	private CategoryRepository repository;

	@Inject
	private ICategoryMapper mapper;

	@Override
	public CategoryDTO toDto(Category entity) {
		return mapper.convert(entity);
	}

	@Override
	public Category toEntity(final Category entity, final CategoryDTO dto) {
		final Category category = mapper.toEntity(dto);
		// On rattache le parent aux enfants
		for (Category children : category.getChildrenCategory()) {
			children.setParentCategory(category);
		}

		return category;
	}

	@Override
	protected Object getPrimaryKey(CategoryDTO dto) {
		return dto.getId();
	}

	@Mapper(componentModel = "cdi", uses = CategoryMapperResolver.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	public interface ICategoryMapper {

		@Mapping(source = "childrenCategory", target = "children")
		CategoryDTO convert(Category entity);

		@Mapping(source = "children", target = "childrenCategory")
		Category toEntity(CategoryDTO dto);

	}

}