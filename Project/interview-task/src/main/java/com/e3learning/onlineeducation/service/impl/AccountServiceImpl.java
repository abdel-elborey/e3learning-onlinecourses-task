package com.e3learning.onlineeducation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.model.Training;
import com.e3learning.onlineeducation.repository.AccountRepository;
import com.e3learning.onlineeducation.repository.AddressRepository;
import com.e3learning.onlineeducation.service.AccountService;
import com.e3learning.onlineeducation.service.TrainingService;
import com.e3learning.onlineeducation.vo.AccountSearchForm;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private TrainingService trainingService;
	
	public AccountServiceImpl(){
		
	}
	public AccountServiceImpl(AccountRepository accountRepository, TrainingService trainingService, AddressRepository addressRepository){
		this.accountRepository = accountRepository;
		this.trainingService = trainingService;
		this.addressRepository = addressRepository;
	}

	@Override
	@Transactional
	public Account saveAccount(Account account) {
		account.setAddress(addressRepository.save(account.getAddress()));
		account = accountRepository.save(account);
		if (account.getTraining() != null) {
			for (Training training : account.getTraining()) {
				training.setAccount(account);
				trainingService.saveTraining(training);
			}
		}
		return account;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Account> findAll() {
		List<Account> accounts = accountRepository.findAll();
		return accounts;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Account> findByFirstNameLike(String name) {
		name = "%" + name + "%";
		List<Account> accounts = accountRepository.findByFirstNameLike(name);
		return accounts;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Account> findByLastNameLike(String name) {
		name = "%" + name + "%";
		List<Account> accounts = accountRepository.findByLastNameLike(name);
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
	public List<Account> findByFirstNameAndLastName(String firstName, String lastName) {
		firstName = "%" + firstName + "%";
		lastName = "%" + lastName + "%";
		List<Account> accounts = accountRepository.findByFirstNameAndLastNameLike(firstName, lastName);
		return accounts;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Account> searchAccounts(AccountSearchForm accountSearchVO) {
		String firstName = accountSearchVO.getFirstName();
		String lastName = accountSearchVO.getLastName();
		
		if (firstName != null && firstName.trim().length() > 0 && lastName != null && lastName.trim().length() > 0) {
			return findByFirstNameAndLastName(firstName, lastName);
		} else if (firstName != null && firstName.trim().length() > 0) {
			return findByFirstNameLike(firstName);
		} else if (lastName != null && lastName.trim().length() > 0) {
			return findByLastNameLike(lastName);
		}
		return accountRepository.findAll();

	}

}
