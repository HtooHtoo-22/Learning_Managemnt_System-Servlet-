package com.lms.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.lms.dto.ClassroomDTO;
import com.lms.entity.AdminEntity;
import com.lms.entity.ClassroomEntity;
import com.lms.repository.AdminRepository;

public class ClassroomMapper {
	
	@Autowired
	private final AdminRepository adminRepo=new AdminRepository();
	
	 public ClassroomDTO toDTO(ClassroomEntity entity) {
	        if (entity == null) {
	            return null;
	        }
	        ClassroomDTO dto = new ClassroomDTO();
	        dto.setId(entity.getId());
	        dto.setTitle(entity.getTitle());
	        dto.setDescription(entity.getDescription());
	        dto.setImageUrl(entity.getImageUrl());
	        dto.setPasscode(entity.getPasscode());
	        dto.setCreatedDate(entity.getCreatedDate());
	        
	        // Extract Admin details from the AdminEntity
	        if (entity.getAdmin() != null) {
	            dto.setAdminId(entity.getAdmin().getId());
	            dto.setAdminName(entity.getAdmin().getName()); // Assuming AdminEntity has a "name" field
	        }

	        return dto;
	    }
	 public ClassroomEntity toEntity(ClassroomDTO dto) {
	        if (dto == null) {
	            return null;
	        }
	        ClassroomEntity entity = new ClassroomEntity();
	        entity.setId(dto.getId());
	        entity.setTitle(dto.getTitle());
	        entity.setDescription(dto.getDescription());
	        entity.setImageUrl(dto.getImageUrl());
	        entity.setPasscode(dto.getPasscode());
	        if (dto.getAdminId() != 0) {
	            AdminEntity admin = adminRepo.retrieveAdminById(dto.getAdminId()); 
	            entity.setAdmin(admin);
	        }

	        return entity;
	    }
}