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
@Table(name="submit_file")
public class SubmissionFileEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private int id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="submission_id",nullable = false)
	private SubmissionEntity submission;
	
	@Column(name = "submit_fileurl",nullable = false)
    private String submitFileUrl;
	
	@Column(name = "submit_date", nullable = false)
    private LocalDateTime submitDate;
	
	@Column(name = "status",nullable = false)
    private int status;
	

	protected void onCreate() {
	    if (status == 0) { // If not explicitly set, assign default
	        status = 1;
	    }
	    
	    LocalDateTime now = LocalDateTime.now();
	    if (submitDate == null) {
	        submitDate = now;
	    }
	}
}
