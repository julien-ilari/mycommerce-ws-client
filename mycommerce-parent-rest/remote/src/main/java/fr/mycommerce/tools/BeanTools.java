package fr.mycommerce.tools;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.deltaspike.core.util.StringUtils;

import fr.mycommerce.service.ID;

/**
 * Classe utilitaire pour la manipulation des beans
 * 
 * @author Julien ILARI
 *
 */
public final class BeanTools {

	/**
	 * Constructeur private (classe utilitaire)
	 */
	private BeanTools() {
	}

	/**
	 * <h1>Copie les propriétés d'un bean à l'autre</h1>
	 * <p>
	 * Ne prends pas en charge la copie de map entre les beans
	 * </p>
	 * 
	 * @param dest
	 * @param orig
	 */
	public final static Object copyProperties(Object dest, Object orig) {
		copyProperties(dest, orig, Collections.emptyMap(), Collections.emptyList());
		
		return dest;
	}

	/**
	 * <h1>Copie les propriétés d'un bean à l'autre</h1>
	 * <p>
	 * Ne prends pas en charge la copie de map entre les beans
	 * </p>
	 * Les types suivants, sont pris en charges :
	 * <ul>
	 * <li>ID<?></li>
	 * <li>Iterable<?></li>
	 * </ul>
	 * 
	 * @param dest
	 * @param orig
	 * @param mapProps
	 * @param propIgnore
	 */
	public final static void copyProperties(final Object dest, final Object orig, final Map<String, String> mapProps, final List<String> propIgnore) {
		if (dest == null && orig == null) {
			// Ignore
		} else if (dest != null && orig == null) {
			// BeanUtils.copyProperties(dest, dest.getClass().newInstance());
		}

		final List<Field> destFields = Arrays.asList(dest.getClass().getDeclaredFields());
		final List<Field> origFields = Arrays.asList(orig.getClass().getDeclaredFields());

		destFields.parallelStream().forEach(destField -> {
			final String destFieldName = destField.getName();

			// Recherche propriété dest dans orig
			final Optional<Field> field = origFields.parallelStream().filter(o -> o.getName().equals(destFieldName))
					.findAny();

			// Match il faut effectuer la copie de orig. vers dest.
			if (field.isPresent()) {
				try {
					// origine
					final Field origField = field.get();
					origField.setAccessible(true);
					final Object origValue = origField.get(orig);
					origField.setAccessible(false);

					// cas origine null
					if (origValue == null || StringUtils.isEmpty(origValue.toString())) {
						destField.setAccessible(true);
						destField.set(dest, null);
						destField.setAccessible(false);
					}
					// copie valeur type ID
					else if (origValue instanceof ID<?>) {
						final Constructor<?> constructor = destField.getType().getDeclaredConstructor();
						constructor.setAccessible(true);

						final Object value = constructor.newInstance();
						copyProperties(value, origValue);
						destField.setAccessible(true);
						destField.set(dest, value);
						destField.setAccessible(false);
					}
					// copie valeur type Iterable
					else if (origValue instanceof Iterable<?>) {

					}
					// copie valeur type Map ...
					else if (origValue instanceof Map<?, ?>) {
						// Ignore
					} else {
						BeanUtils.copyProperty(dest, destFieldName, origValue);
					}
				} catch (Exception e) {
					// TODO : logger exception "Erreur lors de l'accès à une propriété de l'entité",
					// e);
				}

			}

		});
	}

}
