package com.lms.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lms.entity.ClassroomEntity;
import com.lms.entity.TeacherEnrollmentEntity;
import com.lms.entity.TeacherEntity;

import jakarta.persistence.EntityManager;

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
			    "SELECT te FROM TeacherEnrollmentEntity te WHERE te.classroom.id = :n", 
			    TeacherEnrollmentEntity.class
			)
			.setParameter("n", classroomId)
			.getResultList();
		return trEnrollList;
	}
}
