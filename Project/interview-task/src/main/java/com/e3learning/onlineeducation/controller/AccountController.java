package com.e3learning.onlineeducation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.model.AccountStatus;
import com.e3learning.onlineeducation.model.Address;
import com.e3learning.onlineeducation.model.Country;
import com.e3learning.onlineeducation.model.Course;
import com.e3learning.onlineeducation.model.Training;
import com.e3learning.onlineeducation.service.AccountService;
import com.e3learning.onlineeducation.service.CountryService;
import com.e3learning.onlineeducation.service.CourseService;
import com.e3learning.onlineeducation.service.TrainingService;
 
@Controller 
public class AccountController {
	 
	@Autowired
	private AccountService accountService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private TrainingService trainingService;
	
	@RequestMapping(value="addAccount")
	public String addAccount(HttpServletRequest request, HttpServletResponse response){

		Course course = new Course();
		course.setTitle("Object Oriented Programming in C++");
		course = courseService.saveCourse(course);
		
		Country country = countryService.findAll().get(0);
		
		Address address = new Address();
		address.setCountry(country);
		address.setState("VIC");
		address.setStreetName("Roden St");
		address.setSuburb("West Melbourne");
		
		Account account = new Account();		
		account.setEmail("abdo@yata7adaRambo.com"); 
		account.setFirstName("Abdelrahman");
		account.setLastName("Elborey");
		account.setStatus(AccountStatus.ACTIVE);
		account.setAddress(address);		
		account = accountService.saveAccount(account);
		
		Training training  = new Training();
		training.setAccount(account);
		training.setCourse(course);
		training.setStartDate(new java.util.Date());
		
		training = trainingService.saveTraining(training);
		//System.out.println(countryService.findAll().get(0).getName());

		return "result";
	}

}
