package com.e3learning.onlineeducation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.e3learning.onlineeducation.model.Course;
import com.e3learning.onlineeducation.service.CourseService;

@Component
public class CourseValidator implements Validator {

	@Autowired
	CourseService courseService;
	
    @Override
    public boolean supports(Class<?> course) {
      return Course.class.equals(course);
    }

    @Override
    public void validate(Object target, Errors errors) {
      Course course = (Course) target;

      if(courseService.findByTitleLike(course.getTitle(), 0, 5).getContent().size() > 0){
    	  errors.rejectValue("title", "course.title.alreadyexists");
      }
    }

}
