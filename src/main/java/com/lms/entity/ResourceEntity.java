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
@Table(name="resource")
public class ResourceEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private int id;
	
	@Column(name="title",nullable = false,length = 30)
    private String title;
	
	@Column(name="resource_type",nullable = false,length = 15)
    private String resourceType;
	
	@Column(name="link",nullable = true)
	private String link;
	
	@Column(name="file_url")
	private String fileUrl;
	
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
	}
}
