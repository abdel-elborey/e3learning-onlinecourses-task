package com.e3learning.onlineeducation.service;

import java.util.List;

import com.e3learning.onlineeducation.model.Country;

public interface CountryService {

	List<Country> findAll();

	Country findById(Integer id);
}
