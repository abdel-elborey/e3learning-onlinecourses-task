package com.e3learning.onlineeducation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e3learning.onlineeducation.model.Country;

@Repository("countryRepository")
public interface CountryRepository extends JpaRepository<Country, Integer> {
	
}
 