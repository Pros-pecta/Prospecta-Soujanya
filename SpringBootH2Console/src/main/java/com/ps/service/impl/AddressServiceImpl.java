package com.ps.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.model.Address;
import com.ps.repository.AddressRepository;
import com.ps.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressRepository addRepo;
	
	@Override
	public Address getAddress(Integer id) {
		Address address = null;
		Optional<Address> opt = addRepo.findById(id);
		if(opt.isPresent()) {
			address = opt.get();
		}
	    return address;
	}
	
	@Override
	public List<Address> getAllAddresses() {
		return addRepo.findAll();
	}
	
	@Override
	public boolean isAddressExist(Integer id) {
		return addRepo.existsById(id);
	}
	
	@Override
	public Integer saveAddress(Address addr) {
		return addRepo.save(addr).getId();
	}
	
	@Override
	public Address getAddrByPhoneNum(String phNumber) {
		return addRepo.getAddrByPhoneNumber(phNumber);
	}

}
