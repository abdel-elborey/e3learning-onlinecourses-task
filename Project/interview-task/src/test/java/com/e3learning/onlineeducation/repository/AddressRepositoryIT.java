package com.e3learning.onlineeducation.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import testsuits.IntegrationTest;

import com.e3learning.onlineeducation.model.Address;
import com.e3learning.onlineeducation.model.Country;

@Category(IntegrationTest.class)
@ContextConfiguration(locations = "classpath:testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AddressRepositoryIT {
	
	@Autowired
	AddressRepository addressRepository;

	@Autowired
	CountryRepository countryRepository;
	
	@Test
	@Transactional
	public void testSaveAndGetAndDelete() throws Exception {
		
		Address address = new Address();			
		Country country = countryRepository.findOne(1);
		address.setCountry(country);
		address.setState("NewState");
		address.setStreetName("NewStreet");
		address.setSuburb("NewSuburb");
		address = addressRepository.save(address);	
		
		Address recentlyAddeddAddress = addressRepository.findOne(address.getId());
		assertEquals(country.getId(), recentlyAddeddAddress.getCountry().getId());
		assertEquals("NewState", recentlyAddeddAddress.getState());
		
		addressRepository.delete(recentlyAddeddAddress);
		Address deletedAddress = addressRepository.findOne(recentlyAddeddAddress.getId());
		assertNull(deletedAddress);
	}
}
