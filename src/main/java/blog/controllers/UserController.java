package blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.models.User;
import blog.services.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/users")
    public String index(Model model) {
		List<User> allUsers = userService.findAll();
		model.addAttribute("allUsers", allUsers);
        return "users/userList";
    }
}
