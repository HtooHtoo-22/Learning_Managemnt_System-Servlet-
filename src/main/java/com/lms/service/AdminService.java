package com.lms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.dto.TeacherDTO;
import com.lms.entity.TeacherEntity;

@Service
public interface AdminService {
	public List<TeacherEntity> getAllTeachers();
	public boolean createTeacher(TeacherEntity teacher,int adminId);
	public TeacherDTO getTeacherById(int teacherId);
	
}
