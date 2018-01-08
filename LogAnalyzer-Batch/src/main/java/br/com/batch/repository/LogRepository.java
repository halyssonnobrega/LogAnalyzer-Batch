package br.com.batch.repository;

import javax.persistence.EntityManager;

import br.com.batch.configuration.ConnectionFactory;
import br.com.batch.entity.LogEntity;

public class LogRepository extends ConnectionFactory {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5068275207687184383L;
	private static EntityManager em = null;

	public LogRepository() {
		em = getEntityManager();
	}

	public void save(LogEntity log) {
		try {
			em.getTransaction().begin();
			em.persist(log);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
			em.close();
		}
	}
}
