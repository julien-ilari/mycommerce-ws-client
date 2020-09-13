package fr.mycommerce.service.store.product.mapper;

import java.util.List;

import org.apache.deltaspike.data.api.mapping.QueryInOutMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

import fr.mycommerce.service.store.product.ProductDTO;
import fr.mycommerce.service.store.product.ProductLangDTO;
import fr.mycommerce.service.store.product.ProductVariationDTO;
import fr.mycommerce.service.store.product.entity.Product;
import fr.mycommerce.service.store.product.entity.ProductLang;
import fr.mycommerce.service.store.product.entity.ProductVariation;

/**
 * Mapper personnalisé ProductEntity <> ProductDTO
 * 
 * @author Julien ILARI
 *
 */
public class ProductParentMapper {

	@Mapper(config = ProductMappingConfig.class, uses = { ProductResolverMapper.class })
	public interface IProductVariationMapper {
		
		ProductVariation productVariationDTOToProductVariation(ProductVariationDTO dto);

		ProductVariationDTO productVariationToProductVariationDTO(ProductVariation entity);
		
		List<ProductVariationDTO> mapResultList(List<ProductVariation> result);

	}

	@Mapper(config = ProductMappingConfig.class, uses = { ProductResolverMapper.class })
	public interface IProductMapper {

		@Mappings({ @Mapping(target = ".", source = "stock"), @Mapping(target = ".", source = "pricing"),
				@Mapping(target = ".", source = "shipping") })
		Product productDTOToProduct(ProductDTO productDTO);

		@Mappings({ @Mapping(target = "stock", source = "."), @Mapping(target = "pricing", source = "."),
				@Mapping(target = "shipping", source = ".") })
		ProductDTO productToProductDTO(Product entity);

	}

	@Mapper(config = ProductMappingConfig.class, uses = { ProductLangResolverMapper.class, IProductMapper.class })
	public interface IProductLangMapper extends QueryInOutMapper<ProductLang> {

		@Mappings({ @Mapping(target = "id", source = "identity.idProduct"),
				@Mapping(target = "codeLang", source = "identity.codeLang"), @Mapping(target = ".", source = "product"),
				@Mapping(target = "stock", source = "product"), @Mapping(target = "pricing", source = "product"),
				@Mapping(target = "shipping", source = "product"), @Mapping(target = "seo", source = ".")

		})
		@Override
		ProductLangDTO mapResult(ProductLang entity);

		@Mappings({ @Mapping(target = "identity.idProduct", source = "id"),
				@Mapping(target = "identity.codeLang", source = "codeLang"), @Mapping(target = "product", source = "."),
				@Mapping(target = ".", source = "seo") })
		ProductLang toEntity(ProductLangDTO dto);

		@Override
		List<ProductLangDTO> mapResultList(List<ProductLang> result);

		@Override
		default boolean mapsParameter(Object parameter) {
			return parameter != null && (parameter instanceof ProductLangDTO);
		}

		@Override
		default ProductLang mapParameter(Object parameter) {
			return IProductLangMapper.this.toEntity((ProductLangDTO) parameter);
		}

	}

	@MapperConfig(componentModel = "cdi", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	interface ProductMappingConfig {
	}

}