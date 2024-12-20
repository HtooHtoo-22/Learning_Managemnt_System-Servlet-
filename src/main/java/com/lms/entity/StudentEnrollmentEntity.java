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
@Table(name="student_enrollment")
public class StudentEnrollmentEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private int id;
	
	@Column(name = "enrollment_date", nullable = false)
    private LocalDateTime enrollmentDate;
	
	@Column(name = "status",nullable = false)
    private int status;
	
	@ManyToOne
	@JoinColumn(name="student_id",nullable = false)
	private StudentEntity student;
	
	@ManyToOne
	@JoinColumn(name="classroom_id",nullable = false)
	private ClassroomEntity classroom;
	
	@PrePersist
	protected void onCreate() {
	    if (status == 0) { // If not explicitly set, assign default
	        status = 1;
	    }
	    
	    LocalDateTime now = LocalDateTime.now();
	    if (enrollmentDate == null) {
	        enrollmentDate = now;
	    }
	}
}
