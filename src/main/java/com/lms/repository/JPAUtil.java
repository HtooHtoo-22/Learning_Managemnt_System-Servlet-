package com.lms.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
	static EntityManager em=null;
	public static EntityManager getEniEntityManager() {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPATest");
		em=emf.createEntityManager();
		return em;
	}
}
