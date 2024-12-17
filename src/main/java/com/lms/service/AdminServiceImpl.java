package com.lms.service;
import java.util.ArrayList;
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
	public List<TeacherDTO> getAllTeachers(){
		List<TeacherEntity> teacherList=teacherRepo.getAllTeachers();
		List<TeacherDTO> teacherListDTO=new ArrayList<TeacherDTO>();
		for(TeacherEntity teacher:teacherList) {
			if(teacher.getStatus()==1) {
				TeacherDTO teacherDTO=teacherMapper.toDTO(teacher);
				teacherListDTO.add(teacherDTO);
			}
		}
		return teacherListDTO;
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
	@Override
	public void deleteTeacher(int teacherId) {
		teacherRepo.updateTeacherStatusTo0(teacherId);
	}
	@Override
	public List<TeacherDTO> getDeletedTeachers(){
		List<TeacherEntity> teacherList=teacherRepo.getAllTeachers();
		List<TeacherDTO> teacherListDTO=new ArrayList<TeacherDTO>();
		for(TeacherEntity teacher:teacherList) {
			if(teacher.getStatus()==0) {
				TeacherDTO teacherDTO=teacherMapper.toDTO(teacher);
				teacherListDTO.add(teacherDTO);
			}
		}
		return teacherListDTO;
	}
	@Override
	public void restoreTeacher(int teacherId) {
		teacherRepo.updateTeacherStatusTo1(teacherId);
	}
	
}
