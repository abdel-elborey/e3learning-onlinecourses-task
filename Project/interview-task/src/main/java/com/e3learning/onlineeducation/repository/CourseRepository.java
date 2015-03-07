package com.e3learning.onlineeducation.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.e3learning.onlineeducation.model.Course;

@Repository("courseRepository")
public interface CourseRepository extends JpaRepository<Course, Long> {
	Page<Course> findByTitleLike(String title, Pageable pageable);
	
	@Query(value = "SELECT * from courses where id not in(select course_id from training where account_id = ?1)", nativeQuery = true)
	List<Course> findEligibleForAccount(Long accountId);

}
