package com.lms.service;

import org.springframework.stereotype.Service;

import com.lms.dto.LoginDTO;
import com.lms.dto.StudentDTO;
import com.lms.dto.UserDTO;
@Service
public interface UserService {
	public UserDTO doLogin(LoginDTO loginDTO);
	public String validateLogin(LoginDTO loginDTO);
	public String validateEmail(String email);
	
	public void registerstudent(StudentDTO student);
}
