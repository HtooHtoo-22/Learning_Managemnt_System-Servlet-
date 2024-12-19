package com.lms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lms.dto.ClassroomDTO;
import com.lms.dto.StudentDTO;
import com.lms.dto.TeacherDTO;
import com.lms.dto.UserDTO;
import com.lms.service.AdminService;
import com.lms.service.AdminServiceImpl;
import com.lms.service.ClassroomService;
import com.lms.service.ClassroomServiceImpl;
import com.lms.service.StudentService;
import com.lms.service.StudentServiceImpl;

@Controller
public class StudentController {
	
	@Autowired
	private final AdminService adminService=new AdminServiceImpl();
	
	@Autowired
	private final StudentService studentService= new StudentServiceImpl();
	
	@Autowired
	private final ClassroomService classService= new ClassroomServiceImpl();
	
	@GetMapping("/viewStudentClassroom")
	public String viewAllClassrooms(Model model,HttpSession session) {
		List<ClassroomDTO> classList=new ArrayList<ClassroomDTO>();
		UserDTO userDTO=(UserDTO) session.getAttribute("userInfo");
		if(userDTO==null) {
			classList=adminService.getAllClasses();
		}else {
			classList=studentService.getClassesStudentDoesNotEnrollYet(userDTO.getId());
		}
		model.addAttribute("classList", classList);
		return "studentallclass";
	}
	@GetMapping("/viewClassDetailStudent")
	public String viewClassDetailStudent(@RequestParam("id")int classroomId,Model model) {
		ClassroomDTO classroom=adminService.getClassById(classroomId);
		List<TeacherDTO> teacherList=adminService.getTeachersInClassroom(classroomId);
		model.addAttribute("classroom", classroom);
		model.addAttribute("teacherList", teacherList);
		
		return "studentclassdetail";
	}
	@PostMapping("/joinClass")
	public String joinClass(@RequestParam("classroomId")int classId,@RequestParam("passcode")String classPasscode,RedirectAttributes redirectAttributes,HttpSession session) {
		String errorMessage=studentService.validateClassroomPasscode(classId, classPasscode);
		if(errorMessage!=null) {
			redirectAttributes.addFlashAttribute("error", errorMessage);
			return "redirect:/viewClassDetailStudent?id="+classId;
		}else {
			UserDTO studentDTO=(UserDTO) session.getAttribute("userInfo");
			studentService.createStudentEnrollment(studentDTO.getId(), classId);
			classService.updateClassPasscode(classId);
			redirectAttributes.addFlashAttribute("message", "Join Classroom Successfully.");
			return "redirect:/viewMyClassroom";
		}
	}
	@GetMapping("/viewMyClassroom")
	public String viewMyClassroomList(HttpSession session,Model model) {
		UserDTO student=(UserDTO) session.getAttribute("userInfo");
		if(student!=null) {
			List<ClassroomDTO>classList= studentService.getEnrolledClassrooms(student.getId());
			model.addAttribute("classList", classList);
		}
		return "myclass";
	}
	@GetMapping("/viewMyClassDetailStudent")
	public String viewMyClassDetail(@RequestParam("id")int classId,Model model) {
		ClassroomDTO classroom=adminService.getClassById(classId);
		List<TeacherDTO> trList= adminService.getTeachersInClassroom(classId);
		List <StudentDTO> stuList=adminService.getStudentssInClassroom(classId);
		//stuList materialList
		model.addAttribute("teacherList", trList);
		model.addAttribute("studentList", stuList);
		model.addAttribute("classroom", classroom);
		return "studentmyclassdetail";
	}
}
