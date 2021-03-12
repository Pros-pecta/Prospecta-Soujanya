package com.ps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ps.model.Address;
import com.ps.model.User;

public interface AddressRepository extends JpaRepository<Address, Integer>{
	public Address getAddrByPhoneNumber(String phNumber);
}
