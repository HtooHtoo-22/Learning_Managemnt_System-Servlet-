package com.lms.entity;

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
@Table(name="material")
public class MaterialEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private int id;
	
	@Column(name="title",nullable = false,length = 30)
    private String title;
    
    @Column(name="description",nullable = false, length = 100)
    private String description;
    
    @Column(name = "status",nullable = false)
    private int status;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="teacher_id",nullable = false)
	private TeacherEntity teacher;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="classroom_id",nullable = false)
	private ClassroomEntity classroom;
}