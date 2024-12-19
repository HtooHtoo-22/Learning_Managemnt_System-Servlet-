package com.lms.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lms.entity.ClassroomEntity;
import com.lms.entity.StudentEnrollmentEntity;
import com.lms.entity.StudentEntity;
import com.lms.entity.TeacherEnrollmentEntity;
import com.lms.entity.TeacherEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

@Repository
public class TeacherEnrollmentRepository {
	public void insertTeacherEnrollment(TeacherEntity teacher,ClassroomEntity classroom) {
		EntityManager em=JPAUtil.getEniEntityManager();
		em.getTransaction().begin();
		TeacherEnrollmentEntity trEnroll=new TeacherEnrollmentEntity();
		trEnroll.setTeacher(teacher);
		trEnroll.setClassroom(classroom);
		em.persist(trEnroll);
		em.getTransaction().commit();
		em.close();
	}
	public List<TeacherEnrollmentEntity> retrieveTeacherEnrollmentInTheClassroom(int classroomId){
		EntityManager em=JPAUtil.getEniEntityManager();
		List<TeacherEnrollmentEntity> trEnrollList = em.createQuery(
			    "SELECT te FROM TeacherEnrollmentEntity te WHERE te.classroom.id = :n And te.status=1", 
			    TeacherEnrollmentEntity.class
			)
			.setParameter("n", classroomId)
			.getResultList();
		return trEnrollList;
	}
	public List<TeacherEnrollmentEntity> retrieveTeacherEnrollmentByTeacher(TeacherEntity teacher) {
	    EntityManager em = JPAUtil.getEniEntityManager();
	    List<TeacherEnrollmentEntity> trEnrollList = new ArrayList<TeacherEnrollmentEntity>();
	    
	    try {
	        TypedQuery<TeacherEnrollmentEntity> queryClass = em.createQuery(
	            "SELECT te FROM TeacherEnrollmentEntity te WHERE te.teacher = :teacher", 
	            TeacherEnrollmentEntity.class
	        );
	        queryClass.setParameter("teacher", teacher);
	        
	       trEnrollList = queryClass.getResultList();
	    } catch (NoResultException e) {
	        
	    } catch (PersistenceException e) {
	        System.err.println("Database error occurred: " + e.getMessage());
	    } finally {
	        if (em != null && em.isOpen()) {
	            em.close();
	        }
	    }
	    
	    return trEnrollList;
	}
}
