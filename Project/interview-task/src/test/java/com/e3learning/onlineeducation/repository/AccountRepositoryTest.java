package com.e3learning.onlineeducation.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.model.AccountStatus;
import com.e3learning.onlineeducation.model.Address;

@ContextConfiguration(locations = "classpath:testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AccountRepositoryTest {

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	AddressRepository addressRepository;

	@Autowired
	CountryRepository countryRepository;
	
	@Before
	public void setUp() {
		accountRepository.deleteAll();
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
	public void testCount() {
		assertEquals(20, accountRepository.count());
	}

	@Test
	public void testFindByFirstName() {
		Page<Account> account = accountRepository.findByFirstNameLike("%firstName1%", new PageRequest(0, 5));
		System.out.println(account.getContent());
		assertNotNull(account);
		assertEquals(5, account.getNumberOfElements());
		assertEquals(0, account.getNumber());
		assertEquals(5, account.getSize());
		assertEquals(3, account.getTotalPages());
		assertEquals(11, account.getTotalElements());
	}
	
	@Test
	public void testFindByLastName() {
		Page<Account> account = accountRepository.findByLastNameLike("%lastName1%", new PageRequest(0, 5));
		System.out.println(account.getContent());
		assertNotNull(account);
		assertEquals(5, account.getNumberOfElements());
		assertEquals(0, account.getNumber());
		assertEquals(5, account.getSize());
		assertEquals(3, account.getTotalPages());
		assertEquals(11, account.getTotalElements());
	}
}
