package com.lms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="admin")
public class AdminEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 40)
    private String password;
    
    @Column(name = "role", nullable = true, length = 15)
    private String role;
    
    @Column(name = "status", nullable = false)
    private int status;
    @PrePersist
	protected void onCreate() {
	    if (status == 0) { // If not explicitly set, assign default
	        status = 1;
	    }
	}
}
