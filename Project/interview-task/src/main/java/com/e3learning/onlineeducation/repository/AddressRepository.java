package com.e3learning.onlineeducation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e3learning.onlineeducation.model.Address;

@Repository("addressRepository")
public interface AddressRepository extends JpaRepository<Address, Long> {
	
}
