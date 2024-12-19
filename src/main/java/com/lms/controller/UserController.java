package com.lms.controller;

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

import com.lms.dto.LoginDTO;
import com.lms.dto.StudentDTO;
import com.lms.dto.TeacherDTO;
import com.lms.dto.UserDTO;
import com.lms.entity.TeacherEntity;
import com.lms.service.AdminService;
import com.lms.service.AdminServiceImpl;
import com.lms.service.UserService;
import com.lms.service.UserServiceImpl;

@Controller
public class UserController {
	
	@Autowired
	private final UserService userService=new UserServiceImpl();
	
	@Autowired
	private final AdminService adminService=new AdminServiceImpl();
	
	@GetMapping("/")
	public String showHomePage() {
		return "student";
	}
	@GetMapping("/login")
	public ModelAndView showLoginForm(Model model) {
		return new ModelAndView("login","loginObj",new LoginDTO());
	}
	@PostMapping("/doLogin")
	public String doLogin(@ModelAttribute("loginObj")LoginDTO loginDTO,RedirectAttributes redirectAttributes,HttpSession session,Model model) {
		String errorMessage=userService.validateLogin(loginDTO);
		if(errorMessage!=null) {
			redirectAttributes.addFlashAttribute("error", errorMessage);
			return "redirect:/login";
		}else {
			UserDTO userDTO=userService.doLogin(loginDTO);
		
			session.setAttribute("userInfo", userDTO);
			switch(userDTO.getRole()) {
				case "Admin":return "admin";
				case "Teacher":
					boolean isTrAccNew=userService.validateTeacherAccIsNew(userDTO.getPassword());
					if(isTrAccNew) {
						model.addAttribute("teacherId", userDTO.getId());
						return "changeTeacherPassword";
					}else {
						return "teacher";
					}		
				case "Student": return "student";
				default : return "error";
			}
		}
	}
	@PostMapping("/changeTeacherPassword")
	public String changeTeacherPassword(@RequestParam("teacherId")int teacherId,@RequestParam("newPassword")String password,HttpSession session) {
		userService.changeTeacherPassword(teacherId, password);
		TeacherDTO teacher=adminService.getTeacherById(teacherId);
		UserDTO user=new UserDTO();
		user.setId(teacher.getId());
		user.setName(teacher.getName());
		user.setPassword(teacher.getPassword());
		user.setEmail(teacher.getEmail());
		user.setPassword(teacher.getPassword());
		user.setRole("Teacher");
		session.setAttribute("userInfo", user);
		return "teacher";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	@GetMapping("/registerAccount")
	public ModelAndView showRegForm() {
		return new ModelAndView("studentRegForm","studentObj",new StudentDTO());
	}
	@PostMapping("/registerStudent")
	public String registerStudent(@ModelAttribute("studentObj")StudentDTO student,RedirectAttributes redirectAttributes) {
		String errorMessage=userService.validateEmail(student.getEmail());
		if(errorMessage!=null) {
			redirectAttributes.addFlashAttribute("error", errorMessage);
			return "redirect:/registerAccount";
		}
		userService.registerstudent(student);
		return "redirect:/login";
	}
}
