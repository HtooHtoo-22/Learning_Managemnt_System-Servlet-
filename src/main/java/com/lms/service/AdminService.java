package com.lms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.lms.dto.TeacherDTO;
import com.lms.entity.TeacherEntity;

@Service
public interface AdminService {
	public List<TeacherDTO> getAllTeachers();
	public List<TeacherDTO> getDeletedTeachers();
	public boolean createTeacher(TeacherEntity teacher,int adminId);
	public TeacherDTO getTeacherById(int teacherId);
	public void editTeacher(TeacherDTO teacherDTO);
	public void deleteTeacher(int teacherId);
	public void restoreTeacher(int teacherId);
	
}
