package com.lms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.dto.ClassroomDTO;

@Service
public interface StudentService {
	
	public String validateClassroomPasscode(int classId,String passcode);
	public void createStudentEnrollment(int studentId,int classroomId);
	
	public List<ClassroomDTO> getEnrolledClassrooms(int studentId);
	public List<ClassroomDTO> getClassesStudentDoesNotEnrollYet(int studentId);
}
