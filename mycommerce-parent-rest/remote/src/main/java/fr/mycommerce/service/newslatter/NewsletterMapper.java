package fr.mycommerce.service.newslatter;

import java.util.List;

import org.apache.deltaspike.data.api.mapping.QueryInOutMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.NullValuePropertyMappingStrategy;


public class NewsletterMapper {

	@Mapper(config = MappingConfig.class, uses = {NewsletterResolver.class})
	public interface INewsletterMapper extends QueryInOutMapper<NewslatterEntity> {


		NewslatterDTO mapResult(NewslatterEntity entity);

		NewslatterEntity toEntity(NewslatterDTO dto);

		@Override
		List<NewslatterDTO> mapResultList(List<NewslatterEntity> result);

		@Override
		default boolean mapsParameter(Object parameter) {
			return parameter != null && (parameter instanceof NewslatterDTO);
		}

		@Override
		default NewslatterEntity mapParameter(Object parameter) {
			NewslatterEntity pojo =  INewsletterMapper.this.toEntity((NewslatterDTO) parameter);
			
			return pojo;
		}

	}

	@MapperConfig(componentModel = "cdi", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	interface MappingConfig {
	}
}