package com.e3learning.onlineeducation.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import testsuits.IntegrationTest;

import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.model.AccountStatus;
import com.e3learning.onlineeducation.model.Address;
import com.e3learning.onlineeducation.model.Course;
import com.e3learning.onlineeducation.model.Training;
import com.e3learning.onlineeducation.model.TrainingPK;
import com.e3learning.onlineeducation.service.AccountService;
import com.e3learning.onlineeducation.service.CountryService;
import com.e3learning.onlineeducation.service.CourseService;
import com.e3learning.onlineeducation.service.TrainingService;
import com.e3learning.onlineeducation.vo.AccountDetailsForm;
import com.e3learning.onlineeducation.vo.EnrollInCourseForm;
import com.fasterxml.jackson.databind.ObjectMapper;

@Category(IntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:testContext.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AccountControllerIT {
	
	@Autowired
    private WebApplicationContext wac;
	
	@Autowired
    AccountService accountService;

	@Autowired
	CountryService countryService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	TrainingService trainingService;
	
	Course course  = new Course();
	
	Account account = new Account();
	
	Training training = new Training();
    
	private MockMvc mockMvc;

    @Before
    public void setup() {
       this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
       	
        Address address = new Address();			
		address.setCountry(countryService.findById(1));
		address.setState("state");
		address.setStreetName("streetName");
		address.setSuburb("suburb");
		
		account.setAddress(address);
		account.setEmail("email@domain.com");
		account.setFirstName("user1");
		account.setLastName("user1");
		account.setStatus(AccountStatus.ACTIVE);
		account = accountService.saveAccount(account);
				
		course.setTitle("MyNewCourse");
		course.setDescription("MyNewCourse");
		course = courseService.saveCourse(course);
		
		
		TrainingPK trainingPK = new TrainingPK();
		trainingPK.setAccountId(account.getId());
		trainingPK.setCourseId(course.getId());
		training.setTrainingPK(trainingPK);
		training.setAccount(account);
		training.setCourse(course);
		training.setStartDate(new Date());
		training = trainingService.saveTraining(training);
    }
    
    @After
    public void tearDown(){
//    	accountService.deleteById(account.getId());
//    	courseService.deleteById(course.getId());
//    	trainingService.deleteById(training.getTrainingPK());
    }

    @Test
    public void testSearchAccount() throws Exception {
    	this.mockMvc.perform(post("/searchAccounts","json" )
    			.contentType(MediaType.APPLICATION_JSON)
    			.content("{ \"firstName\": \"user1\", \"lastName\": \"\" }".getBytes()))
    			.andExpect(status().isOk())
    			.andExpect(content().contentType("application/json;charset=UTF-8"))
    			.andExpect(jsonPath("$[0].firstName").value("user1"));
    }
    
    @Test
    public void testGetMyDetails() throws Exception {
    	AccountDetailsForm form = new AccountDetailsForm();
    	form.setAccountId(account.getId());
    	ObjectMapper jsonMapper = new ObjectMapper();
        String value =  jsonMapper.writeValueAsString(form);
        
    	this.mockMvc.perform(post("/getMyDetails","json" )
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(value.getBytes()))
    			.andExpect(status().isOk())
    			.andExpect(content().contentType("application/json;charset=UTF-8"))
    			.andExpect(jsonPath("$.training[0].course.title").value("MyNewCourse"));
    }
    
    @Test
    public void testEnrolAccountInCourse() throws Exception {
    	Course courseToEnroll = new Course();
    	courseToEnroll.setTitle("MyNewCourse2");
    	courseToEnroll.setDescription("MyNewCourse2");
    	courseToEnroll = courseService.saveCourse(courseToEnroll);
		
    	EnrollInCourseForm form = new EnrollInCourseForm();
    	form.setAccountId(account.getId());
    	form.setCourseId(courseToEnroll.getId());
    	
    	ObjectMapper jsonMapper = new ObjectMapper();
        String value =  jsonMapper.writeValueAsString(form);
        
    	this.mockMvc.perform(post("/enrollUserInCourse","json" )
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(value.getBytes()))
    			.andExpect(status().isOk())
    			.andExpect(content().contentType("application/json;charset=UTF-8"))
    			.andExpect(jsonPath("$.trainingPK.courseId").exists());
    }
}
