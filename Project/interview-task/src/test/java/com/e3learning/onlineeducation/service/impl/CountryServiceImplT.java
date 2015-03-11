package com.e3learning.onlineeducation.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.e3learning.onlineeducation.IntegrationTest;
import com.e3learning.onlineeducation.model.Country;
import com.e3learning.onlineeducation.service.CountryService;

@Category(IntegrationTest.class)
@ContextConfiguration(locations = "classpath:testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)

public class CountryServiceImplT {


	@Autowired
	CountryService countryService;

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindAll() {
		List <Country> countries = countryService.findAll();
		assertNotNull(countries);
		assertEquals(242, countries.size());
	}

	@Test
	public void testFindById() {
		Country country = countryService.findById(1);
		assertNotNull(country);
	}
}
