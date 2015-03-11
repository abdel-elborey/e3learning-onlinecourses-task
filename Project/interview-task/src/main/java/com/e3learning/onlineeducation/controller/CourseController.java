package com.e3learning.onlineeducation.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.model.Course;
import com.e3learning.onlineeducation.service.CourseService;
import com.e3learning.onlineeducation.validator.CourseValidator;
import com.e3learning.onlineeducation.vo.AccountDetailsForm;
 
@Controller 
public class CourseController {
	
	private static final Logger logger = Logger.getLogger(CourseController.class);
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseValidator courseValidator;
	
	
	@RequestMapping(value = "/getEligibleForAccount" , method=RequestMethod.POST)
	public @ResponseBody List<Course> getEligibleForAccount(@RequestBody AccountDetailsForm accountDetailsForm) {	
		List<Course> courses = null;
		try {
			Account account = new Account();
			account.setId(Long.valueOf(accountDetailsForm.getAccountId()));
			courses = courseService.findEligibleForAccount(account);
			logger.info("Eligible courses for user " + accountDetailsForm.getAccountId() + " are " + courses);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return courses;
	}
	
	@RequestMapping(value = "/courses" , method=RequestMethod.GET)
	public String addAccountForm(Model model) {
		Course course = new Course();
		model.addAttribute("course", course);
		return "add_course";		
	}
	
	@RequestMapping(value = "/courses" , method=RequestMethod.POST)
	public String addAccount(Model model,@Valid @ModelAttribute Course course, BindingResult result){

		String retunPage = "index";
		courseValidator.validate(course,result);		
		if(result.hasErrors()){
			retunPage = "add_course";			
		}else{
			try{
				courseService.saveCourse(course);
				model.addAttribute("message", "Course was Created Successfully");
			} catch(Exception exception){
				if(exception.getMessage().contains("Duplicate")){					
					courseValidator.validate(course,result);
					retunPage = "add_course";	
				}
				exception.printStackTrace();
				logger.error(exception.getMessage());
				model.addAttribute("message","<font style='color: #ff0000;'>Operation Failed Please Contact administrator or try again later</font>");
			}
		}
		return retunPage;
	}

}
