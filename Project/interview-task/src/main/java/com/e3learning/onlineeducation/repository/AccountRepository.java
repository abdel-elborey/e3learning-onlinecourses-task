package com.e3learning.onlineeducation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.e3learning.onlineeducation.model.Account;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {	
	    List<Account> findByFirstNameLike(String firstName);
	    List<Account> findByLastNameLike(String lastName);
	    @Query(value = "SELECT a from Account a where a.firstName like ?1 and a.lastName like ?2)")
	    List<Account> findByFirstNameAndLastNameLike(String firstName, String lastName);
}
