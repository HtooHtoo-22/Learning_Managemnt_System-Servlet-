package com.lms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lms.entity.StudentEntity;

import jakarta.persistence.EntityManager;

@Repository
public class StudentRepository {
	public StudentEntity retrieveStudentById(int studentId) {
		EntityManager em=JPAUtil.getEniEntityManager();
		StudentEntity student=em.find(StudentEntity.class, studentId);
		return student;
	}
	@SuppressWarnings("unchecked")
	public List<StudentEntity> retrieveStudents(){
		EntityManager em=JPAUtil.getEniEntityManager();
		List<StudentEntity> studentList=em.createQuery("SELECT s FROM StudentEntity s").getResultList();
		return studentList;
	}
	public void updateStudentStatus0(int studentId) {
		EntityManager em=JPAUtil.getEniEntityManager();
		StudentEntity student=em.find(StudentEntity.class, studentId);
		if(student!=null) {
			em.getTransaction().begin();
			student.setStatus(0);
			em.merge(student);
			em.getTransaction().commit();
			em.close();
		}else {
			System.out.println("Update Student Status 0 Error");
		}
	}
	public void insertStudent(StudentEntity student) {
		EntityManager em=JPAUtil.getEniEntityManager();
		em.getTransaction().begin();
		em.persist(student);
		em.getTransaction().commit();
		em.close();
	}
}
