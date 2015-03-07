package com.e3learning.onlineeducation.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.model.Course;
import com.e3learning.onlineeducation.service.AccountService;
import com.e3learning.onlineeducation.service.CountryService;
import com.e3learning.onlineeducation.service.CourseService;
import com.e3learning.onlineeducation.service.TrainingService;
 
@Controller 
public class CourseController {
	
	private static final Logger logger = Logger.getLogger(CourseController.class);
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private TrainingService trainingService;
	
	
	@RequestMapping(value = "/getEligibleForAccount/{accountId}")
	public @ResponseBody List<Course> getEligibleForAccount(@PathVariable String accountId) {	
		System.out.println("getting eligible courses for account id " + accountId);
		Account account = new Account();
		account.setId(Long.valueOf(accountId));
		List<Course> courses = courseService.findEligibleForAccount(account);
		System.out.println("eligible courses list size is " + courses.size());
		return courses;
	}

}
