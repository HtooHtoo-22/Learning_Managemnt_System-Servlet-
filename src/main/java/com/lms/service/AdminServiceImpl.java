package com.lms.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.dto.TeacherDTO;
import com.lms.entity.AdminEntity;
import com.lms.entity.TeacherEntity;
import com.lms.mapper.TeacherMapper;
import com.lms.repository.AdminRepository;
import com.lms.repository.TeacherRepository;

import etc.RandomCodeGenerator;
@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private final TeacherRepository teacherRepo=new TeacherRepository();
	
	@Autowired
	private final AdminRepository adminRepo=new AdminRepository();
	
	
	private final TeacherMapper teacherMapper=new TeacherMapper();
	
	@Override
	public List<TeacherEntity> getAllTeachers(){
		List<TeacherEntity> teacherList=teacherRepo.getAllTeachers();
		return teacherList;
	}
	@Override
	public boolean createTeacher(TeacherEntity teacher,int adminId) {
		AdminEntity admin=adminRepo.retrieveAdminById(adminId);
		teacher.setGenerate_password(RandomCodeGenerator.generateCode());
		teacher.setAdmin(admin);
		boolean result=teacherRepo.insertTeacher(teacher);
		if(result) {
			return true;
		}
		return false;
	}
	@Override
	public TeacherDTO getTeacherById(int teacherId) {
		TeacherEntity teacherEntity=teacherRepo.retrieveTeacherById(teacherId);
		TeacherDTO teacherDTO=teacherMapper.toDTO(teacherEntity);
		return teacherDTO;
	}
	@Override
	public void editTeacher(TeacherDTO teacherDTO) {
		TeacherEntity teacherEntity=teacherRepo.retrieveTeacherById(teacherDTO.getId());
		teacherEntity.setName(teacherDTO.getName());
		teacherEntity.setEmail(teacherDTO.getEmail());
		teacherEntity.setAddress(teacherDTO.getAddress());
		teacherEntity.setQualification(teacherDTO.getQualification());
		teacherRepo.updateTeacher(teacherEntity);
	}
	
}
