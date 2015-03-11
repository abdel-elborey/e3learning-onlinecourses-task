package com.e3learning.onlineeducation.vo;

import java.util.ArrayList;
import java.util.List;

import com.e3learning.onlineeducation.model.Course;
import com.e3learning.onlineeducation.model.Training;

public class AccountDetailsVO {

	private List<Training> training = new ArrayList<Training>();
	private List<Course> courses = new ArrayList<Course>();

	public List<Training> getTraining() {
		return training;
	}

	public void setTraining(List<Training> training) {
		this.training = training;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

}
