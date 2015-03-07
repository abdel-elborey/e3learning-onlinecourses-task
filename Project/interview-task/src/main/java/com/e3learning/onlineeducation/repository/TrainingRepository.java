package com.e3learning.onlineeducation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.model.Training;
import com.e3learning.onlineeducation.model.TrainingPK;

@Repository("trainingRepository")
public interface TrainingRepository extends JpaRepository<Training, TrainingPK> {
	
	Page<Training> findByAccount(Account account, Pageable pageable);
	
}
