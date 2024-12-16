package com.lms.repository;

import jakarta.persistence.EntityManager;

public class EntityMain {
	public static void main(String[]args) {
		EntityManager em=JPAUtil.getEniEntityManager();
		em.getTransaction().begin();
		em.getTransaction().commit();
		System.out.println("Success");
	}
}
