package com.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.dto.ClassroomDTO;
import com.lms.entity.ClassroomEntity;
import com.lms.entity.StudentEnrollmentEntity;
import com.lms.entity.StudentEntity;
import com.lms.mapper.ClassroomMapper;
import com.lms.repository.ClassroomRepository;
import com.lms.repository.StudentEnrollmentRepository;
import com.lms.repository.StudentRepository;
import com.lms.repository.UserRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private final ClassroomRepository classRepo = new ClassroomRepository();
	
	@Autowired
	private final StudentRepository studentRepo = new StudentRepository();
	
	@Autowired
	private final StudentEnrollmentRepository studentEnrollRepo = new StudentEnrollmentRepository();
	
	private final ClassroomMapper classMapper =new ClassroomMapper();
	
	
	public String validateClassroomPasscode(int classId,String passcode) {
		String errorMessage=null;
		boolean validatePasscode=classRepo.checkClassroomPasscode(classId, passcode);
		if(!validatePasscode) {
			errorMessage="Wrong Passcode";
		}
		return errorMessage;
	}
	public void createStudentEnrollment(int studentId,int classroomId) {
		ClassroomEntity classEntity=classRepo.retrieveClassById(classroomId);
		StudentEntity studentEntity= studentRepo.retrieveStudentById(studentId);
		studentEnrollRepo.insertStudentEnrollment(studentEntity, classEntity);
	}
	public List<ClassroomDTO> getEnrolledClassrooms(int studentId){
		StudentEntity student=studentRepo.retrieveStudentById(studentId);
		List<StudentEnrollmentEntity> stuEnrollList=studentEnrollRepo.retrieveStudentEnrollmentByStudent(student);
		List<ClassroomDTO> classListDTO=new ArrayList<ClassroomDTO>();
		for(StudentEnrollmentEntity stuEnroll:stuEnrollList) {
			if(stuEnroll.getStatus()==1) {
				ClassroomDTO classDTO=classMapper.toDTO(stuEnroll.getClassroom());
				classListDTO.add(classDTO);
			}
			
		}
		return classListDTO;
	}
	public List<ClassroomDTO> getClassesStudentDoesNotEnrollYet(int studentId){
		StudentEntity student=studentRepo.retrieveStudentById(studentId);
		List<ClassroomEntity> classListEntity= studentEnrollRepo.retrieveStudentDoesNotEnrollmentByStudent(student);
		List<ClassroomDTO> classListDTO=classMapper.toDTOList(classListEntity);
		return classListDTO;
	}
}
