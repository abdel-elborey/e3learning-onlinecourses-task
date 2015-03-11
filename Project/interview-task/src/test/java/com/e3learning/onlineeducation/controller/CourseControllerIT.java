package com.e3learning.onlineeducation.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;

import java.util.List;

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
import com.e3learning.onlineeducation.model.Course;
import com.e3learning.onlineeducation.model.Training;
import com.e3learning.onlineeducation.service.AccountService;
import com.e3learning.onlineeducation.service.CountryService;
import com.e3learning.onlineeducation.service.CourseService;
import com.e3learning.onlineeducation.service.TrainingService;

@Category(IntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:testContext.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CourseControllerIT {
	
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
    }
    
    @After
    public void tearDown(){

    }

    @Test
	public void createCourseForm() throws Exception {
		this.mockMvc.perform(
				post("/courses", "course")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("title", "new title")
					.param("description", "new description"))
				.andExpect(status().isOk());
		List<Course> newlyAdded = courseService.findByTitleLike("new title", 0, 10).getContent();
		assertNotNull(newlyAdded);
		assertTrue(newlyAdded.size() >= 1);
	}

}
