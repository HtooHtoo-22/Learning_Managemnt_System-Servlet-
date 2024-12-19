package com.lms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lms.entity.ClassroomEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

@Repository
public class ClassroomRepository {
	public int insertClassroom(ClassroomEntity classEntity) {
		EntityManager em=JPAUtil.getEniEntityManager();
		em.getTransaction().begin();
		em.persist(classEntity);
		em.getTransaction().commit();
		em.close();
		return classEntity.getId();
	}
	@SuppressWarnings("unchecked")
	public List<ClassroomEntity> retrieveClassrooms(){
		EntityManager em=JPAUtil.getEniEntityManager();
		List<ClassroomEntity> classList=em.createQuery("SELECT c FROM ClassroomEntity c").getResultList();
		return classList;
	}
	public ClassroomEntity retrieveClassById(int classId) {
		EntityManager em=JPAUtil.getEniEntityManager();
		ClassroomEntity classroom=em.find(ClassroomEntity.class, classId);
		return classroom;
	}
	public boolean updateClassStatus0(int classId) {
		EntityManager em=JPAUtil.getEniEntityManager();
		ClassroomEntity classroom=em.find(ClassroomEntity.class, classId);
		if(classroom!=null) {
			em.getTransaction().begin();
			classroom.setStatus(0);
			em.merge(classroom);
			em.getTransaction().commit();
			em.close();
			return true;
		}
		return false;
	}
	public boolean checkClassroomPasscode(int classId, String passcode) {
	    EntityManager em = JPAUtil.getEniEntityManager();
	    try {
	        TypedQuery<ClassroomEntity> queryClass = em.createQuery(
	            "SELECT c FROM ClassroomEntity c WHERE c.id = :id AND c.passcode = :passcode", 
	            ClassroomEntity.class
	        );
	        queryClass.setParameter("id", classId);
	        queryClass.setParameter("passcode", passcode);
	        
	        ClassroomEntity classroom = queryClass.getSingleResult();
	        return classroom != null; // If found, return true
	    } catch (NoResultException e) {
	        return false; // If no result is found, return false
	    } finally {
	        em.close(); // Ensure EntityManager is closed
	    }
	}
	public void updateClassroom(ClassroomEntity classroom) {
		EntityManager em=JPAUtil.getEniEntityManager();
		em.getTransaction().begin();
		em.merge(classroom);
		em.getTransaction().commit();
		em.close();
	}

}
