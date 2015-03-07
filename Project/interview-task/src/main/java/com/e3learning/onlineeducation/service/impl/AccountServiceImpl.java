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
	
	@Transactional
	public Account saveAccount(Account account) {
		account.setAddress(addressRepository.save(account.getAddress()));
		return accountRepository.save(account);
	}


    @Transactional(readOnly = true)
	public Page<Account> findAll(int page, int size) {
		 Pageable pageable = new PageRequest(page, size, new Sort(
	                Direction.DESC, "id"));
	        Page<Account> accounts = accountRepository.findAll(pageable);
	        return accounts;
	}
    
    @Transactional(readOnly = true)
    public Page<Account> findByFirstNameLike(String name, int page, int size) {
        Pageable pageable = new PageRequest(page, size, new Sort(
                Direction.DESC, "id"));
        String q = "%" + name + "%";
        Page<Account> accounts = accountRepository.findByFirstNameLike(q, pageable);
        return accounts;
    }

    @Transactional(readOnly = true)
    public Page<Account> findByLastNameLike(String name, int page, int size) {
        Pageable pageable = new PageRequest(page, size, new Sort(
                Direction.DESC, "id"));
        String q = "%" + name + "%";
        Page<Account> accounts = accountRepository.findByLastNameLike(q, pageable);
        return accounts;
    }

    @Transactional(readOnly = true)
    public Account findById(Long id) {
        Account account = accountRepository.findOne(id);
        return account;
    }


    @Transactional
    public Account update(Account account) {
        return accountRepository.save(account);
    }

    @Transactional
    public void deleteById(Long id) {
    	accountRepository.delete(id);
    }

}
