package com.lms.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.lms.dto.TeacherDTO;
import com.lms.entity.AdminEntity;
import com.lms.entity.TeacherEntity;
import com.lms.repository.AdminRepository;

public class TeacherMapper {
	
	@Autowired
	private final AdminRepository adminRepo=new AdminRepository();
	
	public TeacherDTO toDTO(TeacherEntity teacherEntity) {
	    TeacherDTO teacherDTO = new TeacherDTO();
	    teacherDTO.setId(teacherEntity.getId());
	    teacherDTO.setName(teacherEntity.getName());
	    teacherDTO.setEmail(teacherEntity.getEmail());
	    teacherDTO.setPassword(teacherEntity.getPassword());
	    teacherDTO.setQualification(teacherEntity.getQualification());
	    teacherDTO.setAddress(teacherEntity.getAddress());
	    teacherDTO.setGender(teacherEntity.getGender());
	    teacherDTO.setGenerate_password(teacherEntity.getGenerate_password());
	    teacherDTO.setStatus(teacherEntity.getStatus());
	    if (teacherEntity.getAdmin() != null) {
	        teacherDTO.setAdminId(teacherEntity.getAdmin().getId());
	        teacherDTO.setAdminName(teacherEntity.getAdmin().getName());
	    }
	    return teacherDTO;
	}
	public TeacherEntity toEntity(TeacherDTO teacherDTO) {
	    TeacherEntity teacherEntity = new TeacherEntity();
	    teacherEntity.setId(teacherDTO.getId());
	    teacherEntity.setName(teacherDTO.getName());
	    teacherEntity.setEmail(teacherDTO.getEmail());
	    teacherEntity.setPassword(teacherDTO.getPassword());
	    teacherEntity.setQualification(teacherDTO.getQualification());
	    teacherEntity.setAddress(teacherDTO.getAddress());
	    teacherEntity.setGender(teacherDTO.getGender());
	    teacherEntity.setGenerate_password(teacherDTO.getGenerate_password());
	    teacherEntity.setStatus(teacherDTO.getStatus());
	    if (teacherDTO.getAdminId() != 0) {
	    	AdminEntity admin=adminRepo.retrieveAdminById(teacherDTO.getAdminId());
	        teacherEntity.setAdmin(admin);
	    }
	    
	    return teacherEntity;
	}


}
