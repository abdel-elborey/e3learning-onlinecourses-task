package com.e3learning.onlineeducation.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.e3learning.onlineeducation.vo.AccountSearchForm;
import com.e3learning.onlineeducation.vo.EnrollInCourseForm;
 
@Controller 
public class AccountController {
	
	private static final Logger logger = Logger.getLogger(AccountController.class);
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private TrainingService trainingService;
	
	
	@RequestMapping(value = "/gotoSearch")
	public String gotoSearch() {
		logger.info("gotoSearch is called");
		return "search_accounts";		
	}
	
	@RequestMapping(value = "/searchAccounts")
	public @ResponseBody List<Account> searchAccounts(@RequestBody AccountSearchForm accountSearch) {
		System.out.println("searchAccounts is called with parameters " + accountSearch.getFirstName() + " and " + accountSearch.getLastName());
		Page<Account> accounts = accountService.searchAccounts(accountSearch.getFirstName(), accountSearch.getLastName());
		logger.info("searchAccounts result is " + accounts.getContent());
		return accounts.getContent();		
	}
	
	
	@RequestMapping(value = "/getMyCourses/{accountId}")
	public @ResponseBody List<Training> getMyCourses(@PathVariable String accountId) {	
		System.out.println("getting my courses for account id " + accountId);
		Account account = new Account();
		account.setId(Long.valueOf(accountId));
		List<Training> trainings = trainingService.findByAccount(account, 0, 10).getContent();
		System.out.println("eligible courses list size is " + trainings.size());
		return trainings;
	}
	

	@RequestMapping(value = "/enrollUserInCourse")
	public @ResponseBody Training enrolUserInCourse(@RequestBody EnrollInCourseForm enrollInCourseForm) {
		Training training = new Training();
		Account account = accountService.findById(enrollInCourseForm.getAccountId());
		Course course = courseService.findById(enrollInCourseForm.getCourseId());
		training.setAccount(account); 
		training.setCourse(course);
		training.setStartDate(new Date());
		training = trainingService.saveTraining(training);
		return training;
	}
	 
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
