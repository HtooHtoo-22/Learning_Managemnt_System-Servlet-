package com.lms.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

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

}
