package com.e3learning.onlineeducation.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.e3learning.onlineeducation.IntegrationTest;
import com.e3learning.onlineeducation.model.Course;

@Category(IntegrationTest.class)
@ContextConfiguration(locations = "classpath:testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CourseRepositoryIT {

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Before
	public void setUp() {
		courseRepository.deleteAll();
		for (int i = 1; i <= 20; i++) {
			Course course = new Course();
			course.setTitle("TestCourse" + i);
			courseRepository.save(course);
		}
		courseRepository.flush();
	}

	@Test
	public void testCount() {
		assertEquals(20, courseRepository.count());
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
	
}
