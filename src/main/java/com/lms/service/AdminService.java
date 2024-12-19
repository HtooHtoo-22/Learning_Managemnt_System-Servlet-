package com.lms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.dto.ClassroomDTO;
import com.lms.dto.StudentDTO;
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
	
	public List<StudentDTO> getAllStudents(); 
	public void deleteStudent(int studentId);
	
	public void createClassroom(ClassroomDTO classDTO);
	public List<ClassroomDTO> getAllClasses();
	public void deleteClassroom(int classId);
	public void createTeacherEnrollment(int classroomId,List<Integer> teacherIds);
	
	public List<TeacherDTO> getTeachersInClassroom(int classroomId);
	public List<StudentDTO> getStudentssInClassroom(int classroomId);
	public ClassroomDTO getClassById(int classroomId);
	
}
