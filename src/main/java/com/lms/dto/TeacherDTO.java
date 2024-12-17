package com.lms.dto;

import lombok.Data;

@Data
public class TeacherDTO {
	private int id;
	private String name;
	private String email;
	private String password;
	private String qualification;
	private String address;
	private String gender;
	private String generate_password;
	private int adminId;
	private String adminName;
}
