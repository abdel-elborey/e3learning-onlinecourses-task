package com.e3learning.onlineeducation.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import testsuits.IntegrationTest;

import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.model.AccountStatus;
import com.e3learning.onlineeducation.model.Address;
import com.e3learning.onlineeducation.model.Course;
import com.e3learning.onlineeducation.model.Training;

@Category(IntegrationTest.class)
@ContextConfiguration(locations = "classpath:testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CourseRepositoryIT {

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	TrainingRepository trainingRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Before
	public void setUp() {
		for (int i = 1; i <= 20; i++) {
			Course course = new Course();
			course.setTitle("TestCourse" + i);
			courseRepository.save(course);
		}
		
		courseRepository.flush();
	}

	@Test
	public void testFindByTitleLike() {
		Page<Course> courses = courseRepository.findByTitleLike("%TestCourse1%", new PageRequest(
                0, 5));
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
		address.setCountry(countryRepository.findOne(1));
		address.setState("state");
		address.setStreetName("streetName");
		address.setSuburb("suburb");
		address = addressRepository.save(address);
		
		account.setAddress(address);
		account.setEmail("email@domain.com");
		account.setFirstName("firstName" );
		account.setLastName("lastName" );
		account.setStatus(AccountStatus.ACTIVE);
		account = accountRepository.save(account);
		
		Course nonEligibleCourse = new Course();
		nonEligibleCourse.setTitle("Non Eligible course");
		nonEligibleCourse = courseRepository.save(nonEligibleCourse);
		
		Training training = new Training();
		training.setAccount(account);
		training.setCourse(nonEligibleCourse);
		training.setStartDate(new Date());
		training = trainingRepository.save(training);

		
		Page<Training> trainings = trainingRepository.findByAccount(account, new PageRequest(0, 5));
		assertEquals(1,trainings.getContent().size());
		
		List<Course> eligibleCourses = courseRepository.findEligibleForAccount(account.getId());		
		for(Course c : eligibleCourses){
			assertNotEquals(nonEligibleCourse.getTitle(), c.getTitle());
		}
		
	}
	
}
