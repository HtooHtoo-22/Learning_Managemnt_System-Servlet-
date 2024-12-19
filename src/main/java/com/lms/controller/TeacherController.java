package com.lms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lms.dto.ClassroomDTO;
import com.lms.dto.UserDTO;
import com.lms.service.ClassroomService;
import com.lms.service.ClassroomServiceImpl;

@Controller
public class TeacherController {
	
	@Autowired
	private final ClassroomService classService= new ClassroomServiceImpl();
	
	@GetMapping("/viewMyClassroomTeacher")
	public String viewMyClassroomList(HttpSession session,Model model) {
		UserDTO teacher=(UserDTO) session.getAttribute("userInfo");
		if(teacher!=null) {
			List<ClassroomDTO>classList= classService.getClassThatTrEnroll(teacher.getId());
			model.addAttribute("classList", classList);
		}
		//HEHEHAHA
		return "teacherallclass";
	}
}
