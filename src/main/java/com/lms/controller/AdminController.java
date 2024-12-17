package com.lms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lms.dto.TeacherDTO;
import com.lms.dto.UserDTO;
import com.lms.entity.AdminEntity;
import com.lms.entity.TeacherEntity;
import com.lms.service.AdminService;
import com.lms.service.AdminServiceImpl;
import com.lms.service.UserService;
import com.lms.service.UserServiceImpl;

@Controller
public class AdminController {
	@Autowired
	private final AdminService adminService=new AdminServiceImpl();
	
	@Autowired
	private final UserService userService=new UserServiceImpl();
	
	@GetMapping("/showTeacher")
	public String showTeacherList(Model model) {
		List<TeacherEntity> teacherList=adminService.getAllTeachers();
		model.addAttribute("teacherList", teacherList);
		return "showTeacherList";
	}
	@GetMapping("/createTeacherForm")
	public ModelAndView showCreateTeacher() {
		return new ModelAndView("createTeacherForm","teacherObj",new TeacherEntity());
	}
	@PostMapping("/createTeacher")
	public String createTeacher(@ModelAttribute("teacherObj") TeacherEntity teacher, Model model,HttpSession session) {
	    String errorMessage = userService.validateEmail(teacher.getEmail());
	    if (errorMessage != null) {
	        model.addAttribute("error", errorMessage);
	        return "redirect:/createTeacherForm";
	    }
	    UserDTO userDTO=(UserDTO) session.getAttribute("userInfo");
	    int adminId=userDTO.getId();
	    boolean isCreated = adminService.createTeacher(teacher,adminId);
	    return isCreated ? "redirect:/showTeacher" : "error";
	}
	@GetMapping("/showEditTeacherForm")
	public ModelAndView showEditTrForm(@RequestParam("id")int teacherId,Model model) {
		TeacherDTO teacher=adminService.getTeacherById(teacherId);
		return new ModelAndView("editTeacherForm","teacherObj",teacher);
	}
	@PostMapping("/editTeacher")
	public String editTeacher(@ModelAttribute("teacherObj")TeacherDTO teacherDTO) {
		adminService.editTeacher(teacherDTO);
		return "redirect:/showTeacher";
	}
}
