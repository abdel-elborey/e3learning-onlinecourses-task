package com.e3learning.onlineeducation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.model.Course;
import com.e3learning.onlineeducation.repository.CourseRepository;
import com.e3learning.onlineeducation.service.CourseService;

@Service("CourseService")
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Transactional
	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}

	@Transactional(readOnly = true)
	public List<Course> findAll() {
		List<Course> courses = courseRepository.findAll();
		return courses;
	}

	@Transactional(readOnly = true)
	public Page<Course> findByTitleLike(String title, int page, int size) {
		Pageable pageable = new PageRequest(page, size);
        String q = "%" + title + "%";
        Page<Course> courses = courseRepository.findByTitleLike(q, pageable);
        return courses;
	}
	
	@Transactional(readOnly = true)
	public List<Course> findEligibleForAccount(Account account) { 
        List <Course> courses = courseRepository.findEligibleForAccount(account.getId());
        return courses;
	}

	@Transactional(readOnly = true)
	public Course findById(Long id) {
		Course course = courseRepository.findOne(id);
        return course;
	}

	@Transactional
	public Course update(Course course) {
		 return courseRepository.save(course);
	}

	@Transactional
	public void deleteById(Long id) {
		courseRepository.delete(id);

	}

}
