package fr.mycommerce.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Producteur de entityManager pour la partie persistance
 * 
 * @author Julien ILARI
 *
 */
@ApplicationScoped
public class EntityManagerProducer {

//	@PersistenceUnit
//	private EntityManagerFactory entityManagerFactory;

	@Produces
	@PersistenceContext
	private EntityManager entityManager;

//	@Produces
//	@RequestScoped
//	protected EntityManager createEntityManager() {
//		return this.entityManager;
//	}

	protected void closeEntityManager(@Disposes EntityManager entityManager) {
		if (entityManager.isOpen()) {
			entityManager.close();
		}
	}

//	@Produces
//	@Default
//	@RequestScoped
//	public EntityManager create() {
//		return this.entityManagerFactory.createEntityManager();
//	}

//	public void dispose(@Disposes @Default EntityManager entityManager) {
//		if (entityManager.isOpen()) {
//			entityManager.close();
//		}
//	}

}