package com.lms.mapper;

import com.lms.dto.StudentDTO;
import com.lms.entity.StudentEntity;

public class StudentMapper {
	public StudentDTO toDTO(StudentEntity studentEntity) {
        if (studentEntity == null) {
            return null;
        }
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(studentEntity.getId());
        studentDTO.setName(studentEntity.getName());
        studentDTO.setEmail(studentEntity.getEmail());
        studentDTO.setPassword(studentEntity.getPassword());
        studentDTO.setCity(studentEntity.getCity());
        studentDTO.setGender(studentEntity.getGender());
        
        return studentDTO;
    }
	public StudentEntity toEntity(StudentDTO studentDTO) {
        if (studentDTO == null) {
            return null;
        }
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(studentDTO.getId());
        studentEntity.setName(studentDTO.getName());
        studentEntity.setEmail(studentDTO.getEmail());
        studentEntity.setPassword(studentDTO.getPassword());
        studentEntity.setCity(studentDTO.getCity());
        studentEntity.setGender(studentDTO.getGender());
         
        return studentEntity;
    }
}
