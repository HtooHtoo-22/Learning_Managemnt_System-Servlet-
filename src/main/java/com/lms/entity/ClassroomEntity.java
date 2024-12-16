package com.lms.entity;

import java.time.LocalDateTime;

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
@Table(name="classroom")
public class ClassroomEntity {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
	    private int id;

	    @Column(name="title",nullable = false)
	    private String title;
	    
	    @Column(name="description",nullable = false)
	    private String description;

	    @Column(name = "image_url")
	    private String imageUrl;
	    
	    @Column(name = "passcode",nullable = false)
	    private String passcode;
	    
	    @Column(name = "status",nullable = false)
	    private int status;

	    @Column(name = "created_date", nullable = false)
	    private LocalDateTime createdDate;
	    
	    @PrePersist
	    public void prePersist() {
	        if (this.status == 0) {  // Default status is 1
	            this.status = 1;
	        }
	        if (this.createdDate == null) {
	            this.createdDate = LocalDateTime.now();  // Set default created date if not provided
	        }
	    }
	    @ManyToOne(cascade = CascadeType.PERSIST)
		@JoinColumn(name="admin_id",nullable = false)
		private AdminEntity admin;
}
