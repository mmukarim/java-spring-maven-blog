package blog.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blog.forms.RegisterForm;
import blog.models.User;
import blog.services.NotificationService;
import blog.services.UserService;

@Controller
public class RegisterController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private NotificationService notifyService;
	
	@RequestMapping("/users/register")
	public String registerPage(RegisterForm registerForm) {
		return "users/register";
	}
	
	@RequestMapping(value = "/users/register", method = RequestMethod.POST)
	public String register(@Valid RegisterForm registerForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()){
			notifyService.addErrorMessage("Please fill the form correctly!");
			return "users/register";
		}
		
		final User createdUser = userService.create(new User(registerForm.getUsername(),registerForm.getFullName()));
		if(null == createdUser) {
			notifyService.addErrorMessage("Please fill the form correctly!");
			return "users/register";
		}

		notifyService.addInfoMessage("Registration Succesfull for " +
				null != createdUser.getFullName() ? createdUser.getFullName() : createdUser.getUsername() +
				"Login Now!!!");
		return "redirect:/users/login";
	}
}
