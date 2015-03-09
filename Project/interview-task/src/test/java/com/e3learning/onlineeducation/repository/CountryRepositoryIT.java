package com.e3learning.onlineeducation.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.e3learning.onlineeducation.IntegrationTest;
import com.e3learning.onlineeducation.model.Country;

@Category(IntegrationTest.class)
@ContextConfiguration(locations = "classpath:testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CountryRepositoryIT {


	@Autowired
	CountryRepository countryRepository;
	
	@Test
	public void testFindAll() {
		List <Country> countries = countryRepository.findAll();
		assertNotNull(countries);
		assertEquals(242, countries.size());
	}
}
