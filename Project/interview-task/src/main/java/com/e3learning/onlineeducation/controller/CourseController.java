package com.e3learning.onlineeducation.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.model.Course;
import com.e3learning.onlineeducation.service.CourseService;
 
@Controller 
public class CourseController {
	
	private static final Logger logger = Logger.getLogger(CourseController.class);
	
	@Autowired
	private CourseService courseService;
	
	
	@RequestMapping(value = "/getEligibleForAccount/{accountId}" , method=RequestMethod.GET)
	public @ResponseBody List<Course> getEligibleForAccount(@PathVariable String accountId) {	
		Account account = new Account();
		account.setId(Long.valueOf(accountId));
		List<Course> courses = courseService.findEligibleForAccount(account);
		logger.info("Eligible courses for user " + accountId + " are " + courses);
		return courses;
	}
	
	@RequestMapping(value = "/courses" , method=RequestMethod.GET)
	public String addAccountForm(Model model) {
		Course course = new Course();
		model.addAttribute("course", course);
		return "add_course";		
	}
	
	@RequestMapping(value = "/courses" , method=RequestMethod.POST)
	public String addAccount(@Valid @ModelAttribute Course course, BindingResult result){

		String retunPage = "index";
		if(result.hasErrors()){
			retunPage = "add_course";			
		}else{
			courseService.saveCourse(course);
		}
		return retunPage;
	}

}
