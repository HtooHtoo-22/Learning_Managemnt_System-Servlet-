package com.lms.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="teacher")
public class TeacherEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name",length = 30,nullable = false)
	private String name;
	
	@Column(name="email",length = 50,nullable = false)
	private String email;
	
	@Column(name="password",length = 40,nullable = false)
	private String password="teacher";
	
	@Column(name="qualification",length = 25,nullable = false)
	private String qualification;
	
	@Column(name="address",length = 40,nullable = false)
	private String address;
	
	@Column(name="gender",length = 10,nullable = false)
	private String gender;
	
	@Column(name="generate_password",length = 7,nullable = false)
	private String generate_password;
	
	@Column(name = "status", nullable = false)
	private int status;
	
	@PrePersist
	protected void onCreate() {
	    if (status == 0) { // If not explicitly set, assign default
	        status = 1;
	    }
	}
	
	@ManyToOne
	@JoinColumn(name="admin_id",nullable = false)
	private AdminEntity admin;
}
