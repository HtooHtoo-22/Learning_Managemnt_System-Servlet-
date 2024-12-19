package com.lms.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private int id;           
    private String name;      
    private String email;     
    private String password;  
    private String city;      
    private String gender;
    private String enrollmentDate;
}
