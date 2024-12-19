package com.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.dto.ClassroomDTO;
import com.lms.entity.ClassroomEntity;
import com.lms.entity.StudentEnrollmentEntity;
import com.lms.entity.StudentEntity;
import com.lms.entity.TeacherEnrollmentEntity;
import com.lms.entity.TeacherEntity;
import com.lms.mapper.ClassroomMapper;
import com.lms.repository.ClassroomRepository;
import com.lms.repository.TeacherEnrollmentRepository;
import com.lms.repository.TeacherRepository;

import etc.RandomCodeGenerator;

@Service
public class ClassroomServiceImpl implements ClassroomService{
	
	@Autowired
	private final ClassroomRepository classRepo = new ClassroomRepository();
	
	@Autowired
	private final TeacherRepository teacherRepo=new TeacherRepository();
	
	@Autowired
	private final TeacherEnrollmentRepository trEnrollRepo=new TeacherEnrollmentRepository();
	
	private final ClassroomMapper classMapper=new ClassroomMapper();
	
	public void updateClassPasscode(int classId) {
		ClassroomEntity classroom=classRepo.retrieveClassById(classId);
		classroom.setPasscode(RandomCodeGenerator.generateCode());
		classRepo.updateClassroom(classroom);
	}
	public List<ClassroomDTO> getClassThatTrEnroll(int teacherId){
		TeacherEntity teacher=teacherRepo.retrieveTeacherById(teacherId);
		List<TeacherEnrollmentEntity> trEnrollList=trEnrollRepo.retrieveTeacherEnrollmentByTeacher(teacher);
		List<ClassroomDTO> classListDTO=new ArrayList<ClassroomDTO>();
		for(TeacherEnrollmentEntity trEnroll: trEnrollList) {
			ClassroomDTO classDTO=classMapper.toDTO(trEnroll.getClassroom());
			classListDTO.add(classDTO);
		}
		
			
		
		return classListDTO;
	}
}
