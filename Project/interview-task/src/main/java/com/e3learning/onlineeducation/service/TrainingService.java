package com.e3learning.onlineeducation.service;

import org.springframework.data.domain.Page;

import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.model.Training;
import com.e3learning.onlineeducation.model.TrainingPK;

public interface TrainingService {
	Training saveTraining(Training training);

	Page<Training> findAll(int i, int j);

	Page<Training> findByAccount(Account account, int page, int size);
	
	Training findById(TrainingPK trainingPK);

	// This method can be called when training is finished to set the training
	// date and the acquired grade
	Training update(Training training);

	void deleteById(TrainingPK trainingPK);
	
}
