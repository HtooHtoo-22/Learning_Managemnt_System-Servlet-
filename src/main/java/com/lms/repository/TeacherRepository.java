package com.lms.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lms.dto.TeacherDTO;
import com.lms.entity.AdminEntity;
import com.lms.entity.TeacherEntity;

import etc.RandomCodeGenerator;
import jakarta.persistence.EntityManager;

@Repository
public class TeacherRepository {
	@SuppressWarnings("unchecked")
	public List<TeacherEntity> getAllTeachers(){
		EntityManager em=JPAUtil.getEniEntityManager();
		List<TeacherEntity> teacherList=new ArrayList<TeacherEntity>();
		teacherList=em.createQuery("SELECT t FROM TeacherEntity t").getResultList();
		return teacherList;
	}
	public boolean insertTeacher(TeacherEntity teacher) {
	    EntityManager em = JPAUtil.getEniEntityManager();
	    try {
	        em.getTransaction().begin();
	        em.persist(teacher);
	        em.getTransaction().commit();
	        return true; 
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback(); 
	        }
	        e.printStackTrace();
	        return false;
	    } finally {
	        em.close();
	    }
	}
	public TeacherEntity retrieveTeacherById(int teacherId) {
		EntityManager em=JPAUtil.getEniEntityManager();
		TeacherEntity teacher=em.find(TeacherEntity.class, teacherId);
		return teacher;
	}
	public TeacherEntity retrieveTeacherByEmail(String teacherEmail) {
		EntityManager em=JPAUtil.getEniEntityManager();
		TeacherEntity teacher=	(TeacherEntity) em.createQuery("Select t From TeacherEntity t Where t.email=:email").setParameter("email", teacherEmail)
		.getSingleResult();
		return teacher;
	}
	public void updateTeacher(TeacherEntity teacherEntity) {
	    EntityManager em = null;
	    try {
	        em = JPAUtil.getEniEntityManager(); // Assuming your utility method returns an EntityManager
	        em.getTransaction().begin();
	        
	        // Perform the update
	        em.merge(teacherEntity);
	        
	        // Commit the transaction
	        em.getTransaction().commit();
	    } catch (RuntimeException e) {
	        if (em != null && em.getTransaction().isActive()) {
	            em.getTransaction().rollback(); // Rollback in case of error
	        }
	        throw e; // Rethrow the exception after rollback
	    } finally {
	        if (em != null) {
	            em.close(); // Ensure the EntityManager is closed
	        }
	    }
	}
	public void updateTeacherStatusTo0(int teacherId) {
		EntityManager em=JPAUtil.getEniEntityManager();
		TeacherEntity teacher=em.find(TeacherEntity.class, teacherId);
		if(teacher!=null) {
			em.getTransaction().begin();
			teacher.setStatus(0);
			em.merge(teacher);
			em.getTransaction().commit();
		}
	}
	public void updateTeacherStatusTo1(int teacherId) {
		EntityManager em=JPAUtil.getEniEntityManager();
		TeacherEntity teacher=em.find(TeacherEntity.class, teacherId);
		if(teacher!=null) {
			em.getTransaction().begin();
			teacher.setStatus(1);
			em.merge(teacher);
			em.getTransaction().commit();
		}
	}


}
