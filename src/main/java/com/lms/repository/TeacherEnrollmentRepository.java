package com.lms.repository;

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
}
