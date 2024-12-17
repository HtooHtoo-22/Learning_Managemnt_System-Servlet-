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
@Table(name="assignment")
public class AssignmentEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private int id;
	
	@Column(name="title",nullable = false,length = 30)
    private String title;
	
	@Column(name="description",nullable = false,length= 110)
    private String description;
	
	@Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;
	
	@Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;
	
	@Column(name = "status",nullable = false)
    private int status;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="teacher_id",nullable = false)
	private TeacherEntity teacher;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="material_id",nullable = false)
	private MaterialEntity material;
	
	@PrePersist
	protected void onCreate() {
	    if (status == 0) { // If not explicitly set, assign default
	        status = 1;
	    }
	    LocalDateTime now = LocalDateTime.now();
	    if (startDate == null) {
	        startDate = now;
	    }
	    if (endDate == null) {
	        endDate = now;
	    }
	}
}
