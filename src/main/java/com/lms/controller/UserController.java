package com.lms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lms.dto.LoginDTO;
import com.lms.dto.UserDTO;
import com.lms.entity.TeacherEntity;
import com.lms.service.UserService;
import com.lms.service.UserServiceImpl;

@Controller
public class UserController {
	
	@Autowired
	private final UserService userService=new UserServiceImpl();
	
	@GetMapping("/")
	public String showHomePage() {
		return "student";
	}
	@GetMapping("/login")
	public ModelAndView showLoginForm(Model model) {
		return new ModelAndView("login","loginObj",new LoginDTO());
	}
	@PostMapping("/doLogin")
	public String doLogin(@ModelAttribute("loginObj")LoginDTO loginDTO,RedirectAttributes redirectAttributes,HttpSession session) {
		String errorMessage=userService.validateLogin(loginDTO);
		if(errorMessage!=null) {
			redirectAttributes.addFlashAttribute("error", errorMessage);
			return "redirect:/login";
		}else {
			UserDTO userDTO=userService.doLogin(loginDTO);
			session.setAttribute("userInfo", userDTO);
			switch(userDTO.getRole()) {
				case "Admin":return "admin";
				case "Teacher":return "teacher";
				case "Student": return "student";
				default : return "error";
			}
		}
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
