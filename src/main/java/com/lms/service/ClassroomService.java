package com.lms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.dto.ClassroomDTO;

@Service
public interface ClassroomService {
	public void updateClassPasscode(int classId);
	
}
