package com.e3learning.onlineeducation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e3learning.onlineeducation.model.Account;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {	
	    Page<Account> findByFirstNameLike(String firstName, Pageable pageable);
	    Page<Account> findByLastNameLike(String lastName, Pageable pageable);
}
