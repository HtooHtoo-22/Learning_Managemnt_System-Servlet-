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
@Table(name="submission")
public class SubmissionEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private int id;
	
	@ManyToOne
	@JoinColumn(name="student_id",nullable = false)
	private StudentEntity student;
	
	@ManyToOne
	@JoinColumn(name="assignment_id",nullable = false)
	private AssignmentEntity assignment;
	
	@Column(name = "status",nullable = false)
    private int status;
	

	@PrePersist
	protected void onCreate() {
	    if (status == 0) { // If not explicitly set, assign default
	        status = 1;
	    }
	}

}
