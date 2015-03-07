package com.e3learning.onlineeducation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.repository.AccountRepository;
import com.e3learning.onlineeducation.repository.AddressRepository;
import com.e3learning.onlineeducation.service.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Override
	@Transactional
	public Account saveAccount(Account account) {
		account.setAddress(addressRepository.save(account.getAddress()));
		return accountRepository.save(account);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Account> findAll(int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
		Page<Account> accounts = accountRepository.findAll(pageable);
		return accounts;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Account> findByFirstNameLike(String name, int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
		String q = "%" + name + "%";
		Page<Account> accounts = accountRepository.findByFirstNameLike(q, pageable);
		return accounts;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Account> findByLastNameLike(String name, int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
		String q = "%" + name + "%";
		Page<Account> accounts = accountRepository.findByLastNameLike(q, pageable);
		return accounts;
	}

	@Override
	@Transactional(readOnly = true)
	public Account findById(Long id) {
		Account account = accountRepository.findOne(id);
		return account;
	}

	@Override
	@Transactional
	public Account update(Account account) {
		return accountRepository.save(account);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		accountRepository.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Account> findByFirstNameAndLastName(String firstName, String lastName, int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "id"));
		firstName = "%" + firstName + "%";
		lastName = "%" + lastName + "%";
		Page<Account> accounts = accountRepository.findByFirstNameAndLastName(firstName, lastName, pageable);
		return accounts;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Account> searchAccounts(String firstName, String lastName) {
		if (firstName != null && firstName.trim().length() > 0 && lastName != null && lastName.trim().length() > 0) {
			return findByFirstNameAndLastName(firstName, lastName, 0, 10);
		} else if (firstName != null && firstName.trim().length() > 0) {
			return findByFirstNameLike(firstName, 0, 10);
		} else if (lastName != null && lastName.trim().length() > 0) {
			return findByLastNameLike(lastName, 0, 10);
		}

		Pageable pageable = new PageRequest(0, 10, new Sort(Direction.DESC, "id"));
		return accountRepository.findAll(pageable);

	}

}
