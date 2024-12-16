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
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="teacher_enrollment")
public class TeacherEnrollmentEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private int id;
	
	@Column(name = "enrollment_date", nullable = false)
    private LocalDateTime enrollmentDate;
	
	@Column(name = "status",nullable = false)
    private int status;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="teacher_id",nullable = false)
	private TeacherEntity teacher;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="classroom_id",nullable = false)
	private ClassroomEntity classroom;
}
