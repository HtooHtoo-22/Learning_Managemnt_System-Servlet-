package com.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.dto.LoginDTO;
import com.lms.dto.UserDTO;
import com.lms.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private final UserRepository userRepo = new UserRepository();
	
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
}
