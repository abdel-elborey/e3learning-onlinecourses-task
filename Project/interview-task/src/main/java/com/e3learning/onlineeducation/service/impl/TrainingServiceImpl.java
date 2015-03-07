package com.e3learning.onlineeducation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.model.Training;
import com.e3learning.onlineeducation.model.TrainingPK;
import com.e3learning.onlineeducation.repository.TrainingRepository;
import com.e3learning.onlineeducation.service.TrainingService;

@Service("trainingService")
public class TrainingServiceImpl implements TrainingService {

	@Autowired
	private TrainingRepository trainingRepository;

	@Transactional
	public Training saveTraining(Training training) {
		return trainingRepository.save(training);
	}

	@Transactional(readOnly = true)
	public Page<Training> findAll(int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(
                Direction.ASC, "id"));
        Page<Training> trainings = trainingRepository.findAll(pageable);
        return trainings;
	}

	@Transactional(readOnly = true)
	public Page<Training> findByAccount(Account account, int page, int size) {
		Pageable pageable = new PageRequest(page, size);
        Page<Training> trainings = trainingRepository.findByAccount(account,pageable);
        return trainings;
	}

	@Transactional(readOnly = true)
	public Training findById(TrainingPK trainingPK) {
		Training training = trainingRepository.findOne(trainingPK);
        return training;
	}

	@Transactional
	public Training update(Training training) {
		return trainingRepository.save(training);
	}

	@Transactional
	public void deleteById(TrainingPK trainingPK) {
		trainingRepository.delete(trainingPK);
		
	}

}
