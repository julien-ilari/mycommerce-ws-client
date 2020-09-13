package fr.mycommerce.service.store.product.manager;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import fr.mycommerce.service.DefaultManager;
import fr.mycommerce.service.store.product.ProductLangDTO;
import fr.mycommerce.service.store.product.entity.Product;
import fr.mycommerce.service.store.product.repository.ProductLangRepository;
import fr.mycommerce.service.store.product.repository.ProductRepository;
import lombok.Getter;

@ApplicationScoped
public class ProductManager implements DefaultManager<ProductLangDTO, Long> {
	
	@Inject
	@Getter
	private ProductLangRepository repository;
	
	@Inject
	private ProductRepository ProductRepo;

	public ProductLangDTO read(@NotNull final String lang, @NotNull final Long id) {
		return repository.findByLangAndIdProduct(lang, id);
	}

	public List<ProductLangDTO> list(@NotNull final String lang) {
		return repository.findAllByLang(lang);
	}

	@Transactional
	@Override
	public void deleteById(@NotNull final Long id) {
		final Product product = ProductRepo.findBy(id);
		ProductRepo.remove(product);
	}

	@Transactional
	public ProductLangDTO create(@Valid @NotNull final ProductLangDTO dto) {
		return repository.save(dto);
	};

}