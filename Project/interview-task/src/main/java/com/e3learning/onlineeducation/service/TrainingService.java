package com.e3learning.onlineeducation.service;
 
import java.util.Date;

import org.springframework.data.domain.Page;

import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.model.Course;
import com.e3learning.onlineeducation.model.Training;
import com.e3learning.onlineeducation.model.TrainingPK;

public interface TrainingService {
	Training saveTraining(Training training);

	Page<Training> findAll(int i, int j);

	Page<Training> findByAccount(Account account, int page, int size);
	
	Training findById(TrainingPK trainingPK);
	
	Training enrollAccountInCourse(Account account, Course course, Date startDate);

	Training update(Training training);

	void deleteById(TrainingPK trainingPK);
	
	Training finishTraining(TrainingPK trainingPK, Date finishDate,Integer grade );
	
}
