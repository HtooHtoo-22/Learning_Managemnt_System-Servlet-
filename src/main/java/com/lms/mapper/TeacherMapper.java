package com.lms.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.lms.dto.TeacherDTO;
import com.lms.entity.AdminEntity;
import com.lms.entity.TeacherEntity;
import com.lms.repository.AdminRepository;
import com.lms.repository.TeacherRepository;

public class TeacherMapper {
	
	@Autowired
	private final AdminRepository adminRepo=new AdminRepository();
	
	@Autowired
	private final TeacherRepository teacherRepo=new TeacherRepository();
	
	public TeacherDTO toDTO(TeacherEntity teacherEntity) {
	    TeacherDTO teacherDTO = new TeacherDTO();
	    teacherDTO.setId(teacherEntity.getId());
	    teacherDTO.setName(teacherEntity.getName());
	    teacherDTO.setEmail(teacherEntity.getEmail());
	    teacherDTO.setQualification(teacherEntity.getQualification());
	    teacherDTO.setAddress(teacherEntity.getAddress());
	    teacherDTO.setGender(teacherEntity.getGender());
	    teacherDTO.setGenerate_password(teacherEntity.getGenerate_password());
	    if (teacherEntity.getAdmin() != null) {
	        teacherDTO.setAdminId(teacherEntity.getAdmin().getId());
	        teacherDTO.setAdminName(teacherEntity.getAdmin().getName());
	    }
	    return teacherDTO;
	}
	 public static TeacherEntity toEntity(TeacherDTO dto) {
	        if (dto == null) {
	            return null;
	        }
	        
	        TeacherEntity entity = new TeacherEntity();
	        entity.setId(dto.getId());
	        entity.setName(dto.getName());
	        entity.setEmail(dto.getEmail());
	        entity.setPassword(dto.getPassword());
	        entity.setQualification(dto.getQualification());
	        entity.setAddress(dto.getAddress());
	        entity.setGender(dto.getGender());
	        entity.setGenerate_password(dto.getGenerate_password());
	        if (dto.getAdminId() != 0) { 
	            AdminEntity admin = new AdminEntity();
	            admin.setId(dto.getAdminId());
	            entity.setAdmin(admin);
	        }
	        
	        return entity;
	    }



}
