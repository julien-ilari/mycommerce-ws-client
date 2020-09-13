package fr.mycommerce.service.store.product.manager;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import fr.mycommerce.service.store.product.ProductVariationDTO;
import fr.mycommerce.service.store.product.entity.Product;
import fr.mycommerce.service.store.product.entity.ProductVariation;
import fr.mycommerce.service.store.product.mapper.ProductParentMapper.IProductVariationMapper;
import fr.mycommerce.service.store.product.repository.ProductRepository;
import fr.mycommerce.service.store.product.repository.ProductVariationRepository;

@ApplicationScoped
public class ProductVariationManager {

	@Inject
	private ProductRepository productRepo;
	
	@Inject
	private ProductVariationRepository variationRepo;

	@Inject
	private IProductVariationMapper mapper;

	public List<ProductVariationDTO> list(@NotNull final Long productId) {
		return mapper.mapResultList(getProductById(productId).getVariations());
	}

	/**
	 * <h1>Création variation produit</h1>
	 * <p>
	 * Demande de création d'une nouvelle variation du produit.
	 * </p>
	 * 
	 * @param productId identifiant technique du produit
	 * @param variation dto de la variation à créer
	 * @return
	 */
	@Transactional
	public ProductVariationDTO create(@NotNull final Long productId, @NotNull final ProductVariationDTO variation) {
		final Product product = getProductById(productId);

		final ProductVariation entity = mapper.productVariationDTOToProductVariation(variation);
		product.getVariations().add(entity);
		entity.setProduct(product);
		variationRepo.save(entity);

		return mapper.productVariationToProductVariationDTO(entity);
	}

	@Transactional
	public void delete(@NotNull final Long productId, @NotNull final List<Long> ids) {
		final Product product = getProductById(productId);
		product.getVariations().removeIf(o -> ids.contains(o.getId()));

		productRepo.save(product);
	}

	private Product getProductById(final Long productId) {
		return Optional.ofNullable(productRepo.findBy(productId)).orElseThrow(
				() -> new RuntimeException(String.format("Aucun produit avec l'identifiant %s", productId)));
	}

}