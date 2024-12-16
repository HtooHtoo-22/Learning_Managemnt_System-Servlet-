package com.lms.repository;

import org.springframework.stereotype.Repository;

import com.lms.entity.AdminEntity;

import jakarta.persistence.EntityManager;

@Repository
public class AdminRepository {
	public AdminEntity retrieveAdminById(int adminId) {
		EntityManager em=JPAUtil.getEniEntityManager();
		AdminEntity admin=em.find(AdminEntity.class, adminId);
		return admin;
	}
}
