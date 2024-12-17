package com.lms.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ClassroomDTO {
    private int id;                   
    private String title;            
    private String description;       
    private String imageUrl;          
    private String passcode;                     
    private LocalDateTime createdDate; 
    private int adminId;
    private String adminName;
    private MultipartFile imageFile;
    private List<Integer> teachers;
}
