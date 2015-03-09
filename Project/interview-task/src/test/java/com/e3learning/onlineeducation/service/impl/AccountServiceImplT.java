package com.e3learning.onlineeducation.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.e3learning.onlineeducation.IntegrationTest;
import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.model.AccountStatus;
import com.e3learning.onlineeducation.model.Address;
import com.e3learning.onlineeducation.repository.AccountRepository;
import com.e3learning.onlineeducation.service.AccountService;
import com.e3learning.onlineeducation.service.CountryService;

@ContextConfiguration(locations = "classpath:testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Category(IntegrationTest.class)
public class AccountServiceImplT {


	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AccountService accountService;

	@Autowired
	CountryService countryService;

	@Before
	public void setUp() throws Exception {
		accountRepository.deleteAll();
		for (int i = 1; i <= 20; i++) {
			Account account = new Account();

			Address address = new Address();
			address.setCountry(countryService.findById(1));
			address.setState("state");
			address.setStreetName("streetName");
			address.setSuburb("suburb");

			account.setAddress(address);
			account.setEmail("email@domain.com");
			account.setFirstName("firstName" + i);
			account.setLastName("lastName" + i);
			account.setStatus(AccountStatus.ACTIVE);

			accountService.saveAccount(account);
		}
		accountRepository.flush();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindAll00() {
//		Page<Account> p = accountService.findAll(0, 5);
//		assertNotNull(p);
//		List<Account> accounts = p.getContent();
//		assertNotNull(accounts);
//		assertEquals(5, accounts.size());
//		assertEquals(5, p.getNumberOfElements());
//		assertEquals(0, p.getNumber());
//		assertEquals(5, p.getSize());
//		assertEquals(4, p.getTotalPages());
//		assertEquals(20, p.getTotalElements());
	}

	@Test
	public void testFindAll01() {
//		Page<Account> p = accountService.findAll(1, 5);
//		assertNotNull(p);
//		List<Account> accounts = p.getContent();
//		assertNotNull(accounts);
//		assertEquals(5, accounts.size());
//		assertEquals(5, p.getNumberOfElements());
//		assertEquals(1, p.getNumber());
//		assertEquals(5, p.getSize());
//		assertEquals(4, p.getTotalPages());
//		assertEquals(20, p.getTotalElements());
	}

	@Test
	public void testFindByNameLike() throws Exception {
//		Page<Account> p = accountService.findByFirstNameLike("name1", 0, 5);
//		System.out.println(p.getContent());
//		assertNotNull(p);
//		assertEquals(5, p.getNumberOfElements());
//		assertEquals(0, p.getNumber());
//		assertEquals(5, p.getSize());
//		assertEquals(3, p.getTotalPages());
//		assertEquals(11, p.getTotalElements());
	}

	@Test
	public void testFindById() {
//		Account lastOne = accountService.findAll(0, 1).getContent().get(0);
//		Long id = lastOne.getId();
//		Account account = accountService.findById(id);
//		assertEquals(id, account.getId());
//		assertEquals(lastOne.getFirstName(), account.getFirstName());
//		assertEquals(lastOne.getLastName(), account.getLastName());
	}

	@Test
	public void testInsert() {
//		Account lastOne = accountService.findAll(0, 1).getContent().get(0);
//
//		Account account = new Account();
//
//		Address address = new Address();
//		address.setCountry(countryService.findById(1));
//		address.setState("state");
//		address.setStreetName("streetName");
//		address.setSuburb("suburb");
//
//		account.setAddress(address);
//		account.setEmail("email@domain.com");
//		account.setFirstName("firstName");
//		account.setLastName("lastName");
//		account.setStatus(AccountStatus.ACTIVE);
//
//		Account result = accountService.saveAccount(account);
//		accountRepository.flush();
//		assertEquals(Long.valueOf(lastOne.getId() + 1), result.getId());
	}

	@Test
	public void testUpdate() {
//		Account lastOne = accountService.findAll(0, 1).getContent().get(0);
//		lastOne.setFirstName("AfterUpdate");
//		accountService.update(lastOne);
//		Account account = accountService.findById(lastOne.getId());
//		assertEquals(account.getFirstName(), "AfterUpdate");
	}

	@Test
	public void testDeleteById() {
//		Account lastOne = accountService.findAll(0, 1).getContent().get(0);
//		accountService.deleteById(lastOne.getId());
//		accountRepository.flush();
//		Account account = accountService.findById(lastOne.getId());
//		assertNull(account);
	}
}
