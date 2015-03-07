package com.e3learning.onlineeducation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e3learning.onlineeducation.model.Course;

@Repository("courseRepository")
public interface CourseRepository extends JpaRepository<Course, Long> {
	Page<Course> findByTitleLike(String title, Pageable pageable);
}
