package com.codingdojo.bookClub.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.bookClub.models.LoginUser;
import com.codingdojo.bookClub.models.User;
import com.codingdojo.bookClub.services.UserService;

@Controller
public class UserController {
    
	@Autowired
	private UserService userService;
	
	
    @GetMapping("/")
    public String index(Model model) {
    
        // Bind empty User and LoginUser objects to the JSP to capture the form input
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "logreg.jsp";
    }
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        
        // TO-DO Later -- call a register method in the service to do some extra validations and create a new user!
        User registeredUser = userService.register(newUser,  result);
    	
    	
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "logreg.jsp";
        }
        
        session.setAttribute("userId", registeredUser.getId());
        session.setAttribute("username", registeredUser.getUserName());
        return "redirect:/books";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        //Instantiate the user 
    	User loginUser = userService.login(newLogin, result);

       	
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "logreg.jsp";
        }
    
        //Store ID and User name in session
        session.setAttribute("userId", loginUser.getId());
        session.setAttribute("username", loginUser.getUserName());
        return "redirect:/books";
    }
    
    //logout invalidates the session
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
    
    
}
