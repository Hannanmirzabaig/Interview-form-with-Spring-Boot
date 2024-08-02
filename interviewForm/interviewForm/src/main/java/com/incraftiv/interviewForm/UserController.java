package com.incraftiv.interviewForm;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.model.Model;


@RestController
@RequestMapping("/hannan")
//@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping  
	public String index()
	{  
	//returns to index.html
	return"index";  
	}  
//	@PostMapping(value="/save")  
//	public ModelAndView save(@ModelAttribute User user)
//	{  	
//	ModelAndView modelAndView = new ModelAndView();  
//	modelAndView.setViewName("user-data");      
//	modelAndView.addObject("user", user);   
//	userService.saveUser(user);
//	return modelAndView; 
//	}
	
	
	@PostMapping(value="/save")  
	public ModelAndView save(@ModelAttribute User user) {  
	    ModelAndView modelAndView = new ModelAndView();
	    
	    if (userService.emailExists(user.getEmail())) {
	        modelAndView.setViewName("dublicateEmail"); // Set view for duplicate email [dublicateEmail.html]
	       // modelAndView.addObject("message", "Email already exists");
	    } else {
	        userService.saveUser(user);
	        modelAndView.setViewName("user-data"); // Set view for successful save
	        modelAndView.addObject("user", user);
	    }
	    
	    return modelAndView; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping(value = "/getInterviewDetailById/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		User user=userService.getDetailById(id);
//	    User user = userService.getUserById(id).orElse(null);
	    if (user == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok(user);
	}

	
	
	 @GetMapping(value = "/user-name")
	 public List<String> getAll(@RequestParam("name") String name){
		 List<String> user= userService.getDetailService(name);
		 if(user!=null) {
			 return user;
		 }else {
			 return null;
		 }
	 }
	 
	 /*
	  * this API return all related or similar name when i  written one letter two letter , name etc
	  * Custom query
	  */
	 @GetMapping(value = "/user-namee")
	 public List<User> getController(@RequestParam String name){
		 return userService.getDetailServicee(name);
	 }
	
	@GetMapping(value = "/getInterviewDetailByEmail")
	public ResponseEntity<User> getDetailByEmail(@RequestParam String email){
		User u=userService.getDetailByemail(email).orElse(null);
		if(u==null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(u);
		}
	}
	
	 @GetMapping(value = "/getAll")
	    public List<User> getAllUsers() {
	        return userService.getAllUsers();
	    }
	
//	@GetMapping(value = "/getAllDetailsByCustomeQuery")
//	public List<User> getAllDetailsCustomeQuery(){
//		return userService.getAllDetailsCustome();
//	}
	 
	 @DeleteMapping("/deleteById/{id}")
	    public void deleteUser(@PathVariable Long id) {
	        userService.deleteUser(id);
	    }

}
