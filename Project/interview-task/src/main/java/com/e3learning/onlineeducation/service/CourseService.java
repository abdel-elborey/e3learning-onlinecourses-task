package com.e3learning.onlineeducation.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.model.Course;


public interface CourseService {
	
	Course saveCourse(Course course);

	Page<Course> findAll(int i, int j);

	Page<Course> findByTitleLike(String title, int page, int size);

	List<Course> findEligibleForAccount(Account account);
	
	Course findById(Long id);

	Course update(Course course);

	void deleteById(Long id);
}
