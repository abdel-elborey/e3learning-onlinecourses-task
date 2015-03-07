package com.e3learning.onlineeducation.service;

import org.springframework.data.domain.Page;

import com.e3learning.onlineeducation.model.Account;

public interface AccountService {

	Account saveAccount(Account account);

	Page<Account> findAll(int page, int size);

	Page<Account> findByFirstNameLike(String name, int page, int size);

	Page<Account> findByLastNameLike(String name, int page, int size);

	Account findById(Long id);

	Account update(Account account);

	void deleteById(Long id);
}
