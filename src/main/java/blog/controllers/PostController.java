package blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blog.forms.CreatePostForm;
import blog.models.Post;
import blog.models.User;
import blog.services.NotificationService;
import blog.services.PostService;
import blog.services.UserService;

@Controller
public class PostController {
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NotificationService notifyService;
	
	@RequestMapping("/posts")
    public String list(Model model) {
		List<Post> allPosts = postService.findAll();
		model.addAttribute("allPosts", allPosts);
        return "posts/postList";
    }
	
	@RequestMapping("/posts/view/{id}")
	public String view(@PathVariable("id") Long id, Model model) {
		Post post = postService.findById(id);
		if(post == null) {
			notifyService.addErrorMessage("Post not available with ID: "+id);
			return "redirect:/";
		}
		model.addAttribute("post", post);
		return "posts/view";
	}
	
	@RequestMapping("/posts/create")
    public String createPage(CreatePostForm createPostForm, Model model) {
		List<User> allUsers = userService.findAll();
		model.addAttribute("allUsers", allUsers);
        return "posts/create";
    }
	
	@RequestMapping(value = "/posts/create", method = RequestMethod.POST)
	public String createPost(@Valid CreatePostForm createPostForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()){
			notifyService.addErrorMessage("Please fill the form correctly!");
			return "posts/create";
		}
		
		final Post createdPost = postService.create(
				new Post(createPostForm.getTitle(), createPostForm.getBody(), userService.findById(Long.parseLong(createPostForm.getAuthorId()))));
		if(null == createdPost) {
			notifyService.addErrorMessage("Post has error!");
			return "posts/create";
		}

		notifyService.addInfoMessage("Post created!!!");
		return "redirect:/posts/view/" + createdPost.getId();
	}
	
}
