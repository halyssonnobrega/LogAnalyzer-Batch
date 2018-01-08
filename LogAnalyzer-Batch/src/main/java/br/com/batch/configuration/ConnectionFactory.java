package br.com.batch.configuration;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8424383321294898668L;
	private static EntityManagerFactory emF = null;
	private static EntityManager em = null;

	public static EntityManager getEntityManager() {
		try {
			if (emF == null) {
				emF = Persistence.createEntityManagerFactory("datasource");
			}
			em = emF.createEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return em;
	}
}