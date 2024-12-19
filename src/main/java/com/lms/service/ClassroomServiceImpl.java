package com.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.entity.ClassroomEntity;
import com.lms.repository.ClassroomRepository;

import etc.RandomCodeGenerator;

@Service
public class ClassroomServiceImpl implements ClassroomService{
	
	@Autowired
	private final ClassroomRepository classRepo = new ClassroomRepository();
	
	public void updateClassPasscode(int classId) {
		ClassroomEntity classroom=classRepo.retrieveClassById(classId);
		classroom.setPasscode(RandomCodeGenerator.generateCode());
		classRepo.updateClassroom(classroom);
	}
}
