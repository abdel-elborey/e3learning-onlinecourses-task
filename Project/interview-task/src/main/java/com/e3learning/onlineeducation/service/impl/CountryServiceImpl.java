package com.e3learning.onlineeducation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e3learning.onlineeducation.model.Country;
import com.e3learning.onlineeducation.repository.CountryRepository;
import com.e3learning.onlineeducation.service.CountryService;

@Service("CountryService")
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;

	@Transactional(readOnly = true)
	public List<Country> findAll() {
		return countryRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Country findById(Integer id) {
		return countryRepository.findOne(id);
	}


}
