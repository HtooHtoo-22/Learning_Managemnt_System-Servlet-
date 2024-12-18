package com.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.dto.LoginDTO;
import com.lms.dto.StudentDTO;
import com.lms.dto.UserDTO;
import com.lms.entity.StudentEntity;
import com.lms.mapper.StudentMapper;
import com.lms.repository.StudentRepository;
import com.lms.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private final UserRepository userRepo = new UserRepository();
	
	@Autowired
	private final StudentRepository stuRepo = new StudentRepository();
	
	private final StudentMapper studentMapper=new StudentMapper();
	@Override
	public UserDTO doLogin(LoginDTO loginDTO) {
		UserDTO userDTO=null;
		String role=userRepo.getTableNameByEmail(loginDTO.getEmail());
		if(role.equals("Admin")) {
			if(userRepo.checkAdminPassword(loginDTO)!=null) {
				userDTO=userRepo.checkAdminPassword(loginDTO);
			}	
		}else if(role.equals("Teacher")) {
			if(userRepo.checkTeacherPassword(loginDTO)!=null) {
				userDTO=userRepo.checkTeacherPassword(loginDTO);
			}
		}else if(role.equals("Student")) {
			if(userRepo.checkStudentPassword(loginDTO)!=null) {
				userDTO=userRepo.checkStudentPassword(loginDTO);
			}
		}
		return userDTO;
	}
	@Override
	public String validateLogin(LoginDTO loginDTO) {
		String errorMessage=null;
		String role=userRepo.getTableNameByEmail(loginDTO.getEmail());
		if(role==null) {
			errorMessage="Invalid Email!";
		}else if(userRepo.checkAdminPassword(loginDTO)==null && userRepo.checkTeacherPassword(loginDTO)==null 
				&& userRepo.checkStudentPassword(loginDTO)==null){
			errorMessage="Incorrect Password!";
		}
		return errorMessage;
	}
	@Override
	public String validateEmail(String email) {
		String errorMessage=null;
		String role=userRepo.getTableNameByEmail(email);
		if(role!=null) {
			errorMessage="Email is already used!";
		}
		return errorMessage;
	}
	@Override
	public void registerstudent(StudentDTO student) {
		StudentEntity studentEntity=studentMapper.toEntity(student);
		stuRepo.insertStudent(studentEntity);
	}
}
