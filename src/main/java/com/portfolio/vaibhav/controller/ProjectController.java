package com.portfolio.vaibhav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.portfolio.vaibhav.entities.Contact;
import com.portfolio.vaibhav.entities.Prompt;
import com.portfolio.vaibhav.helper.Message;
import com.portfolio.vaibhav.service.ContactService;

import jakarta.servlet.http.HttpSession;


@Controller
public class ProjectController {

	@Autowired
	private ContactService contactService;
	
	
	
	
	
	@RequestMapping("/")
	public String homePage() {
		return "index";
	}
	
	
	
	  @RequestMapping(path="/contact")
		public String contactPage(Model model) {
		
			model.addAttribute("contact",new Contact());
			return "contact";
			}
	
	  @RequestMapping(value="/register",method = RequestMethod.POST)
	  public String addcontactPage(@ModelAttribute("contact") Contact contact,HttpSession session,Model model ) {
		  try {
			  
			 contactService.addContact(contact);
			 session.setAttribute("message", new Message("Message Sent Successfully", "alert-success"));
			  model.addAttribute("contact",new Contact());
		  }
		  catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message", new Message("Something went wrong Please All Fields Are Required " , "alert-danger"));
           
			
		}
		  return "contact";
	  }
	  
	
	  
	  
	  @RequestMapping(path="/about")
	  public String aboutPage() {
		  return "about";
	  }
	  
	 
	  
	  
	  
	  @GetMapping("/ai")
	    public String aiPage(Model model) {
	        model.addAttribute("prompt", new Prompt());
	        return "ai";
	    }

	    @PostMapping("/response")
	    public String getResponse(@ModelAttribute("prompt") Prompt prompt, Model model) {
	        try {
	            Prompt responsePrompt = contactService.getResponse(prompt);
	            responsePrompt.setPrompt("");
	            model.addAttribute("prompt", responsePrompt);  // Show prompt + response
	            return "ai";
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "error";
	        }
	    }
	  
	    
	    
	    
	    
	  

	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	  
	
}
