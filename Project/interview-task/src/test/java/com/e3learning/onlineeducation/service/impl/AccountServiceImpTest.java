package com.e3learning.onlineeducation.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.model.Address;
import com.e3learning.onlineeducation.repository.AccountRepository;
import com.e3learning.onlineeducation.repository.AddressRepository;
import com.e3learning.onlineeducation.service.AccountService;
import com.e3learning.onlineeducation.service.TrainingService;

public class AccountServiceImpTest {


	AccountRepository accountRepository;

	AccountService accountService;
	
	AddressRepository addressRepository;

	TrainingService trainingService;
	
	List<Account> accountsDB = new ArrayList<Account>();
	
	@Before
	public void setUp() throws Exception {
		accountRepository = mock(AccountRepository.class);
		trainingService = mock(TrainingService.class);
		addressRepository = mock(AddressRepository.class);
		accountService = new AccountServiceImpl(accountRepository, trainingService, addressRepository);	

		when(addressRepository.save(any(Address.class))).thenAnswer(new Answer<Address>() {
			@Override
			public Address answer(InvocationOnMock invocation) throws Throwable {
				Address address = (Address) invocation.getArguments()[0];
				address.setId(1L);
				return address;
			}
		});
		
		when(accountRepository.save(any(Account.class))).thenAnswer(new Answer<Account>() {
			@Override
			public Account answer(InvocationOnMock invocation) throws Throwable {
				Account account = (Account) invocation.getArguments()[0];
				account.setId(1L);
				accountsDB.add(account);
				return account;
			}
		});
		
		when(accountRepository.findAll()).thenAnswer(new Answer<List<Account>>() {
			@Override
			public List<Account> answer(InvocationOnMock invocation) throws Throwable {
				return accountsDB;
			}
		});

		
		when(accountRepository.findByFirstNameLike(any(String.class))).thenAnswer(new Answer<List<Account>>() {
			@Override
			public List<Account> answer(InvocationOnMock invocation) throws Throwable {
				String firstName = (String) invocation.getArguments()[0];
				List<Account> results = new ArrayList<Account>();
				for(Account account : accountsDB){
					if(account.getFirstName().contains("%" + firstName + "%"))
						results.add(account);
				}
				return results;
			}
		});

		when(accountRepository.findByLastNameLike(any(String.class))).thenAnswer(new Answer<List<Account>>() {
			@Override
			public List<Account> answer(InvocationOnMock invocation) throws Throwable {
				String lastName = (String) invocation.getArguments()[0];
				List<Account> results = new ArrayList<Account>();
				for(Account account : accountsDB){
					if(account.getLastName().contains("%" + lastName + "%"))
						results.add(account);
				}
				return results;
			}
		});

	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	@Test
	public void testSaveAccount() {
		Account account = new Account();
		account.setLastName("firstName");
		account.setFirstName("lastName");
		account.setEmail("firstName@test.com");
		
		Address address = new Address();
		address.setState("state");
		address.setStreetName("streetName");
		address.setSuburb("suburb");
		
		account.setAddress(address);

		assertNull(account.getId());

		account = accountService.saveAccount(account);

		assertNotNull(account.getId());
		assertTrue(account.getId() > 0);
	}
	
	@Test
	public void testFindAll() {
		
		Account account = new Account();
		account.setLastName("firstName");
		account.setFirstName("lastName");
		account.setEmail("firstName@test.com");
		
		Address address = new Address();
		address.setState("state");
		address.setStreetName("streetName");
		address.setSuburb("suburb");
		
		account.setAddress(address);
		account = accountService.saveAccount(account);

		assertNotNull(accountService.findAll());
		assertTrue(accountService.findAll().size() > 0);
	}
	
	@Test
	public void testFindByFirstName() {
		
		Account account = new Account();
		account.setLastName("firstName");
		account.setFirstName("lastName");
		account.setEmail("firstName@test.com");
		
		Address address = new Address();
		address.setState("state");
		address.setStreetName("streetName");
		address.setSuburb("suburb");
		
		account.setAddress(address);
		account = accountService.saveAccount(account);

		assertNotNull(accountService.findByFirstNameLike(account.getFirstName()));
	}
	
	@Test
	public void testFindByLastName() {
		
		Account account = new Account();
		account.setLastName("firstName");
		account.setFirstName("lastName");
		account.setEmail("firstName@test.com");
		
		Address address = new Address();
		address.setState("state");
		address.setStreetName("streetName");
		address.setSuburb("suburb");
		
		account.setAddress(address);
		account = accountService.saveAccount(account);

		assertNotNull(accountService.findByLastNameLike(account.getLastName()));
	}
}
