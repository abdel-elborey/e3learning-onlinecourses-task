package com.e3learning.onlineeducation.service;

import java.util.List;

import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.vo.AccountSearchForm;

public interface AccountService {

	Account saveAccount(Account account);

	List<Account> findAll();

	List<Account> findByFirstNameLike(String name);

	List<Account> findByLastNameLike(String name);
	
	List<Account> findByFirstNameAndLastName(String firstName, String lastName);

	Account findById(Long id);

	Account update(Account account);

	void deleteById(Long id);

	List<Account> searchAccounts(AccountSearchForm accountSearchVO);
}
