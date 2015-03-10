package com.e3learning.onlineeducation.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

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
import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.model.AccountStatus;
import com.e3learning.onlineeducation.model.Address;

@Category(IntegrationTest.class)
@ContextConfiguration(locations = "classpath:testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)

public class AccountRepositoryIT { 

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	AddressRepository addressRepository;

	@Autowired
	CountryRepository countryRepository;
	
	@Before
	public void setUp() {
		for (int i = 1; i <= 20; i++) {
			Account account = new Account();
			
			Address address = new Address();			
			address.setCountry(countryRepository.findOne(1));
			address.setState("state");
			address.setStreetName("streetName");
			address.setSuburb("suburb");
			address = addressRepository.save(address);
			
			account.setAddress(address);
			account.setEmail("email@domain.com");
			account.setFirstName("firstName" + i);
			account.setLastName("lastName" + i);
			account.setStatus(AccountStatus.ACTIVE);
			accountRepository.save(account);
		}
		accountRepository.flush();
	}

	@Test
	public void testFindByFirstName() {
		List <Account> account = accountRepository.findByFirstNameLike("%firstName1%");
		assertNotNull(account);
		assertEquals(11, account.size());
	}
	
	@Test
	public void testFindByLastName() {
		List<Account> account = accountRepository.findByLastNameLike("%lastName1%");
		assertNotNull(account);
		assertEquals(11, account.size());
	}
	@Test
	public void testFindByFirstAndLastName() {
		List<Account> account = accountRepository.findByFirstNameAndLastNameLike("%firstName1%","%lastName1%");
		assertNotNull(account);
		assertEquals(11, account.size());
	}
}
