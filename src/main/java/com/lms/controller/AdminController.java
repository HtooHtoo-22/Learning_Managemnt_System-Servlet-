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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lms.dto.ClassroomDTO;
import com.lms.dto.StudentDTO;
import com.lms.dto.TeacherDTO;
import com.lms.dto.UserDTO;
import com.lms.entity.AdminEntity;
import com.lms.entity.TeacherEntity;
import com.lms.service.AdminService;
import com.lms.service.AdminServiceImpl;
import com.lms.service.CloudinaryService;
import com.lms.service.UserService;
import com.lms.service.UserServiceImpl;



@Controller
public class AdminController {
	@Autowired
	private final AdminService adminService=new AdminServiceImpl();
	
	@Autowired
	private final UserService userService=new UserServiceImpl();
	
	
	
//===========================Teacher Management=============================================
	@GetMapping("/showTeacher")
	public String showTeacherList(Model model) {
		List<TeacherDTO> teacherList=adminService.getAllTeachers();
		model.addAttribute("teacherList", teacherList);
		return "showTeacherList";
	}
	@GetMapping("/createTeacherForm")
	public ModelAndView showCreateTeacher() {
		return new ModelAndView("createTeacherForm","teacherObj",new TeacherEntity());
	}
	@PostMapping("/createTeacher")
	public String createTeacher(@ModelAttribute("teacherObj") TeacherEntity teacher, RedirectAttributes redirectAttributes,HttpSession session) {
	    String errorMessage = userService.validateEmail(teacher.getEmail());
	    if (errorMessage != null) {
	        redirectAttributes.addFlashAttribute("error", errorMessage);
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
	@GetMapping("/deleteTeacher")
	public String deleteTeacher(@RequestParam("id")int teacherId) {
		adminService.deleteTeacher(teacherId);
		return "redirect:/showTeacher";
	}
	@GetMapping("/showDeletedTeachers")
	public String showDeletedTeacher(Model model) {
		List<TeacherDTO> teacherList=adminService.getDeletedTeachers();
		model.addAttribute("teacherList", teacherList);
		return "showDeletedTeacher";
	}
	@PostMapping("/restoreTeacher")
	public String restoreTeacher(@RequestParam("teacherId")int teacherId) {
		adminService.restoreTeacher(teacherId);
		return "redirect:/showDeletedTeachers";
	}
//===========================Student Management=============================================
	@GetMapping("/showStudent")
	public String showStudent(Model model) {
		List<StudentDTO> studentList=adminService.getAllStudents();
		model.addAttribute("studentList", studentList);
		return "showStudentList";
	}
	@GetMapping("/deleteStudent")
	public String deleteStudent(@RequestParam("studentId")int studentId) {
		adminService.deleteStudent(studentId);
		return "redirect:/showStudent";
	}
//==========================Classroom Management=====================================================
	@GetMapping("/showClassroom")
	public String showClassroom(Model model) {
		List<ClassroomDTO> classList=adminService.getAllClasses();
		model.addAttribute("classList", classList);
		return "classroomList";
	}
	@GetMapping("/showCreateClass")
	public ModelAndView showCreateClass(Model model,HttpSession session) {
		List<TeacherDTO> teacherList=adminService.getAllTeachers();
		UserDTO adminDTO=(UserDTO) session.getAttribute("userInfo");
		model.addAttribute("adminName", adminDTO.getName());
		model.addAttribute("teacherList", teacherList);
		return new ModelAndView("createClassForm","classObj",new ClassroomDTO());
	}
	@PostMapping("/createClass")
	public String createClass(@ModelAttribute("classObj")ClassroomDTO classDTO,HttpSession session) {
		UserDTO admin=(UserDTO) session.getAttribute("userInfo");
		classDTO.setAdminId(admin.getId());
		adminService.createClassroom(classDTO);
		return "redirect:/showClassroom";
	}
	@GetMapping("/deleteClass")
	public String deleteClass(@RequestParam("id")int classId) {
		adminService.deleteClassroom(classId);
		return "redirect:/showClassroom";
	}
	@GetMapping("/viewClassroomDetail")
	public String viewClassroomDetail(@RequestParam("id")int classId,Model model) {
		List<TeacherDTO> trList=adminService.getTeachersInClassroom(classId);
		List <StudentDTO> stuList=adminService.getStudentssInClassroom(classId);
		//List <MaterialDTO>
		ClassroomDTO classroom=adminService.getClassById(classId);
		
		model.addAttribute("studentList", stuList);
		model.addAttribute("classroom", classroom);
		model.addAttribute("teacherList", trList);
		return "viewClassDetail";
		
	}
}
