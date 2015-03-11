package com.e3learning.onlineeducation.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.e3learning.onlineeducation.IntegrationTest;
import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.model.AccountStatus;
import com.e3learning.onlineeducation.model.Address;
import com.e3learning.onlineeducation.model.Course;
import com.e3learning.onlineeducation.model.Training;
import com.e3learning.onlineeducation.service.AccountService;
import com.e3learning.onlineeducation.service.CountryService;
import com.e3learning.onlineeducation.service.CourseService;
import com.e3learning.onlineeducation.service.TrainingService;

@Category(IntegrationTest.class)
@ContextConfiguration(locations = "classpath:testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CourseServiceImplT {


	@Autowired
	CourseService courseService;

	@Autowired
	AccountService accountService;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	TrainingService	trainingService;
	
	@Before
	public void setUp() throws Exception {
		for (int i = 1; i <= 20; i++) {
			Course course = new Course();
			course.setTitle("TestCourse" + i);
			courseService.saveCourse(course);
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindByTitleLike() {
		Page<Course> courses = courseService.findByTitleLike("TestCourse1", 0, 5);
		assertNotNull(courses);
        assertEquals(5, courses.getNumberOfElements());
        assertEquals(0, courses.getNumber());
        assertEquals(5, courses.getSize());
        assertEquals(3, courses.getTotalPages());
        assertEquals(11, courses.getTotalElements());
	}

	@Test 
	public void testFindEligibleForAccount(){
		Account account = new Account();
		
		Address address = new Address();			
		address.setCountry(countryService.findById(1));
		address.setState("state");
		address.setStreetName("streetName");
		address.setSuburb("suburb");		
		account.setAddress(address);
		
		account.setEmail("email@domain.com");
		account.setFirstName("firstName" );
		account.setLastName("lastName" );
		account.setStatus(AccountStatus.ACTIVE);
		account = accountService.saveAccount(account);
		
		Course nonEligibleCourse = new Course();
		nonEligibleCourse.setTitle("Non Eligible course");
		nonEligibleCourse = courseService.saveCourse(nonEligibleCourse);
		
		Training training = new Training();
		training.setAccount(account);
		training.setCourse(nonEligibleCourse);
		training.setStartDate(new Date());
		training = trainingService.saveTraining(training);

		
		Page<Training> trainings = trainingService.findByAccount(account,0, 5);
		assertEquals(1,trainings.getContent().size());
		
		List<Course> eligibleCourses = courseService.findEligibleForAccount(account);		
		for(Course c : eligibleCourses){
			assertNotEquals(nonEligibleCourse.getTitle(), c.getTitle());
		}
		
	}
}
