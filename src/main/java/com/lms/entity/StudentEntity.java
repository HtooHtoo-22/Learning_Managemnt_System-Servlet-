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
@Table(name="student")
public class StudentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name",length = 30,nullable = false)
	private String name;
	
	@Column(name="email",length = 50,nullable = false)
	private String email;
	
	@Column(name="password",length = 40,nullable = false)
	private String password;
	
	@Column(name="city",length = 30,nullable = false)
	private String city;
	
	@Column(name="gender",length = 10,nullable = false)
	private String gender;
	
	@Column(name = "status", nullable = false)
	private int status;
	
	@PrePersist
	protected void onCreate() {
	    if (status == 0) { // If not explicitly set, assign default
	        status = 1;
	    }
	}
	
	
}
