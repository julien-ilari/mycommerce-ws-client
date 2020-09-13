package fr.mycommerce.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.deltaspike.data.api.EntityRepository;

/**
 * Manager générique
 * @author Julien ILARI
 *
 * @param <E> entity type du bean
 * @param <I> primary key type de l'identifiant technique du bean
 */
public interface DefaultManager<E extends ID<I>, I extends Serializable> {

	EntityRepository<E, I> getRepository();

	static int max = 25;

	/**
	 * Demande toutes les occurences existantes
	 * 
	 * @param primaryKey identifiant technique du bean
	 * @return
	 */
	default List<E> list() {
		return getRepository().findAll();
	}

	/**
	 * Demande de création d'une nouvelle occurence
	 * 
	 * @param e bean pour création
	 * @return
	 */
	@Transactional
	default E create(@Valid @NotNull final E e) {
		return getRepository().save(e);
	};
	
	/**
	 * Demande la mise à jour d'une occurence
	 * 
	 * @param e bean pour mise à jour
	 * @return
	 */
	@Transactional
	default E update(@Valid @NotNull final E e) {
		return getRepository().save(e);
	};

	/**
	 * Demande la ou les occurences
	 * 
	 * @param primaryKey
	 * @return
	 */
	default E read(@NotNull final I primaryKey) {
		return getRepository().findBy(primaryKey);
	}
	
	/**
	 * Demande de suppression d'une occurence
	 * Optional
	 * @param entity
	 */
	@Transactional
	default void delete(@Valid final E e) {
		getRepository().remove(e);
	}

	/**
	 * Demande la suppression via l'identifiant technique du bean
	 * 
	 * @param entity
	 */
	@Transactional
	default void deleteById(@NotNull final I primaryKey) {
		final E entity = getRepository().findBy(primaryKey);
		if (entity == null) {
			throw new IllegalArgumentException("primaryKey inconnue");
		}
		
		getRepository().remove(entity);
	}

}