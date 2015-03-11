package com.e3learning.onlineeducation.service.impl;

import static org.junit.Assert.*;

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

import testsuits.IntegrationTest;

import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.model.AccountStatus;
import com.e3learning.onlineeducation.model.Address;
import com.e3learning.onlineeducation.service.AccountService;
import com.e3learning.onlineeducation.service.CountryService;

@Category(IntegrationTest.class)
@ContextConfiguration(locations = "classpath:testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)

public class AccountServiceImplT {

	@Autowired
	AccountService accountService;

	@Autowired
	CountryService countryService;

	@Before
	public void setUp() throws Exception {
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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindById() {
		Account lastOne = accountService.findAll().get(0);
		Long id = lastOne.getId();
		Account account = accountService.findById(id);
		assertEquals(id, account.getId());
		assertEquals(lastOne.getFirstName(), account.getFirstName());
		assertEquals(lastOne.getLastName(), account.getLastName());
	}

	@Test
	public void testInsert() {
		Account account = new Account();

		Address address = new Address();
		address.setCountry(countryService.findById(1));
		address.setState("state");
		address.setStreetName("streetName");
		address.setSuburb("suburb");

		account.setAddress(address);
		account.setEmail("email@domain.com");
		account.setFirstName("firstName");
		account.setLastName("lastName");
		account.setStatus(AccountStatus.ACTIVE);

		Account result = accountService.saveAccount(account);
		Account check = accountService.findById(result.getId());
		assertNotNull(check);
	}

	@Test
	public void testUpdate() {
		Account lastOne = accountService.findAll().get(0);
		lastOne.setFirstName("AfterUpdate");
		accountService.update(lastOne);
		Account account = accountService.findById(lastOne.getId());
		assertEquals(account.getFirstName(), "AfterUpdate");
	}

	@Test
	public void testDeleteById() {
		Account lastOne = accountService.findByFirstNameAndLastName("firstName1", "lastName1").get(0);
		accountService.deleteById(lastOne.getId());
		Account account = accountService.findById(lastOne.getId());
		assertNull(account);
	}
	@Test
	public void testFindByFirstName() {
		List <Account> account = accountService.findByFirstNameLike("%firstName1%");
		assertNotNull(account);
		assertEquals(11, account.size());
	}
	
	@Test
	public void testFindByLastName() {
		List<Account> account = accountService.findByLastNameLike("%lastName1%");
		assertNotNull(account);
		assertEquals(11, account.size());
	}
	@Test
	public void testFindByFirstAndLastName() {
		List<Account> account = accountService.findByFirstNameAndLastName("%firstName1%","%lastName1%");
		assertNotNull(account);
		assertEquals(11, account.size());
	}
}
