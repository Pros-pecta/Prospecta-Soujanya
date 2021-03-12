package com.ps.service;

import java.util.List;

import com.ps.model.Address;

public interface AddressService {
	Integer saveAddress(Address addr);
	List<Address> getAllAddresses();
	Address getAddress(Integer id);
	boolean isAddressExist(Integer id);
	Address getAddrByPhoneNum(String phNumber);
}
	