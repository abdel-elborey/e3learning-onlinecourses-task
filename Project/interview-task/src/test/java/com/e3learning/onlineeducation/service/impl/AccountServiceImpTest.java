package com.e3learning.onlineeducation.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.repository.AccountRepository;
import com.e3learning.onlineeducation.service.AccountService;
import com.e3learning.onlineeducation.service.CountryService;

public class AccountServiceImpTest {


	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AccountService accountService;

	@Autowired
	CountryService countryService;
	

	@Before
	public void setUp() throws Exception {
		accountRepository = mock(AccountRepository.class);
        accountService = new AccountServiceImpl(accountRepository);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	@Test
	public void testSaveAccount() {

		Account account = new Account();
		account.setLastName("formLast");
		account.setFirstName("formFirst");
		account.setEmail("form@test.com");

		when(accountRepository.save(any(Account.class))).thenAnswer(new Answer<Account>() {
			@Override
			public Account answer(InvocationOnMock invocation) throws Throwable {
				Account account = (Account) invocation.getArguments()[0];
				account.setId(1L);
				return account;
			}
		});

		assertNull(account.getId());

		account = accountService.saveAccount(account);

		assertNotNull(account.getId());
		assertTrue(account.getId() > 0);
	}
}
