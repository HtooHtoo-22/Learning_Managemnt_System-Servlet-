package com.lms.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lms.entity.ClassroomEntity;
import com.lms.entity.StudentEnrollmentEntity;
import com.lms.entity.StudentEntity;
import com.lms.entity.TeacherEnrollmentEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

@Repository
public class StudentEnrollmentRepository {
	public void insertStudentEnrollment(StudentEntity student,ClassroomEntity classroom) {
		EntityManager em=JPAUtil.getEniEntityManager();
		em.getTransaction().begin();
		StudentEnrollmentEntity stuEnroll=new StudentEnrollmentEntity();
		stuEnroll.setClassroom(classroom);
		stuEnroll.setStudent(student);
		em.persist(stuEnroll);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<StudentEnrollmentEntity> retrieveStudentEnrollmentByStudent(StudentEntity student) {
	    EntityManager em = JPAUtil.getEniEntityManager();
	    List<StudentEnrollmentEntity> stuEnrollList = new ArrayList<>();
	    
	    try {
	        TypedQuery<StudentEnrollmentEntity> queryClass = em.createQuery(
	            "SELECT se FROM StudentEnrollmentEntity se WHERE se.student = :student", 
	            StudentEnrollmentEntity.class
	        );
	        queryClass.setParameter("student", student);
	        
	       stuEnrollList = queryClass.getResultList();
	    } catch (NoResultException e) {
	        
	    } catch (PersistenceException e) {
	        System.err.println("Database error occurred: " + e.getMessage());
	    } finally {
	        if (em != null && em.isOpen()) {
	            em.close();
	        }
	    }
	    
	    return stuEnrollList;
	}
	public List<ClassroomEntity> retrieveStudentDoesNotEnrollmentByStudent(StudentEntity student) {
	    EntityManager em = JPAUtil.getEniEntityManager();
	    List<ClassroomEntity> classList = new ArrayList<>();
	    
	    try {
	        TypedQuery<ClassroomEntity> queryClass = em.createQuery(
	            "SELECT se.classroom FROM StudentEnrollmentEntity se WHERE se.student != :student And se.status=1", 
	            ClassroomEntity.class
	        );
	        queryClass.setParameter("student", student);
	        
	        classList = queryClass.getResultList();
	    } catch (NoResultException e) {
	        
	    } catch (PersistenceException e) {
	        System.err.println("Database error occurred: " + e.getMessage());
	    } finally {
	        if (em != null && em.isOpen()) {
	            em.close();
	        }
	    }
	    return classList;
	}
	public List<StudentEnrollmentEntity> retrieveStudentEnrollmentInTheClassroom(int classroomId){
		EntityManager em=JPAUtil.getEniEntityManager();
		List<StudentEnrollmentEntity> stuEnrollList = em.createQuery(
			    "SELECT se FROM StudentEnrollmentEntity se WHERE se.classroom.id = :n", 
			    StudentEnrollmentEntity.class
			)
			.setParameter("n", classroomId)
			.getResultList();
		return stuEnrollList;
	}
}
