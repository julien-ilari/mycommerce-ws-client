package fr.mycommerce.service;

import org.apache.deltaspike.data.api.mapping.SimpleQueryInOutMapperBase;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import static fr.mycommerce.tools.BeanTools.copyProperties;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>Défaut Mapper</h1>
 * <p>
 * Mapper par défaut, vous pouvez partir sur la création d'une implémentation
 * personnalisée pour les méthodes si besoin est.
 * </p>
 *
 * <p>
 * Ne prend pas en charge l'héritage multiple (prévu dans une prochaine version).
 * On peut toujours redéfinir newDto en contournement afin de fournir une nouvelle instance du dto.
 * </p>
 *
 * @param <Entity> bean dao
 * @param <Dto>    bean de transfert
 * @author Julien ILARI
 */
@Deprecated
public abstract class AbstractMapper<Entity extends ID<?>, Dto extends ID<?>>
        extends SimpleQueryInOutMapperBase<Entity, Dto> {

	@Inject
	protected EntityManager em;

    /**
     * Liste des propriéts à mapper
     * <ul>
     * <li>La clé correspondant à la propriété de l'origine</li>
     * <li>La valeur corespondant à la propriété de la destination</li>
     * </ul>
     * <p>
     * Utilse lorsque le nom de la propriété orig. est différente de celle de la
     * dest.
     * </p>
     * <p>
     * Par défaut la map est déjà initilisée
     * </p>
     */
    protected final Map<String, String> propMap = new HashMap<>();

    /**
     * Liste des propriétés de la destination à ne pas inclure. Ceci peut-être utile
     * dans le cas ou nous voulons mapper manuellement une proopriété.
     */
    protected final List<String> propIgnore = new ArrayList<>();

    /**
     * <h1>Profondeur de la copie</h1>
     * <h2>Exemple (level = 0) :</h2>
     * <p>
     * la classe A à une propriété type B qui a elle même une propriété de type C.
     * </p>
     * <p>
     * Valeur par défaut 0
     * </p>
     */
    protected int level = 0;

    /**
     * Demande la clé primaire du dto
     */
    @Override
    protected Object getPrimaryKey(final Dto dto) {
        return dto.getId();
    }

    /**
     * Demande la création d'une nouvelle instance type dto
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Dto newDto() {
        final Type type = this.getClass().getGenericSuperclass();

        // TODO : ajouter la prise en charge multi héritage
        if (type instanceof ParameterizedType) {
            final ParameterizedType parameterizedType = (ParameterizedType) type;
            final Class<Dto> dtoClass = (Class<Dto>) parameterizedType.getActualTypeArguments()[1];

            try {
                final Constructor<Dto> constructor = dtoClass.getDeclaredConstructor();
                constructor.setAccessible(true);
                return constructor.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("impossible d'instancier un nouveau dto", e);
            }
        }

        // Il faut vérifier de la classe enfant extends bien directement celle-ci.
        throw new RuntimeException("impossible de déterminer le type du dto");
    }

    /**
     * Transformation en DTO
     */
    @Override
    public Dto toDto(final Entity entity) {
        final Dto dto = newDto();
        copyProperties(dto, entity, propMap, propIgnore);

        return dto;
    }

    /**
     * Transformation d'un Dto en type Entity
     */
    @Override
    public Entity toEntity(final Entity entity, final Dto dto) {
        if (entity == null && dto != null && dto.getId() != null) {
            // Identifiant présent dans dto mais entity non trouvé
            throw new IllegalArgumentException("identifiant présent dans dto mais entity non trouvé");
        }

        copyProperties(entity, dto);

        return entity;
    }
    



}