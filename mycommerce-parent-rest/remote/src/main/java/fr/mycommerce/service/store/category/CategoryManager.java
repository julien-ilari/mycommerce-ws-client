package fr.mycommerce.service.store.category;

import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import fr.mycommerce.service.DefaultManager;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApplicationScoped
@NoArgsConstructor
public class CategoryManager implements DefaultManager<CategoryDTO, Long> {

	@Inject
	@Getter
	private CategoryRepository repository;

	@Override
	public List<CategoryDTO> list() {
		final List<CategoryDTO> values = repository.findAll();
		Collections.sort(values);

		return values;
	}

	@Transactional
	@Override
	public void deleteById(@NotNull final Long id) {
		repository.deleteById(id);
	}

}