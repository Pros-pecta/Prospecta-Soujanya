package com.ps.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.exceptions.UserGetCustomException;
import com.ps.exceptions.UserPostCustomException;
import com.ps.exceptions.UserUpdateCustomException;
import com.ps.model.Address;
import com.ps.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class); 
	
	@Autowired
	private AddressService addrService;
	
	@PostMapping
	public ResponseEntity<String> saveAddressDetails(@Valid @RequestBody(required = true) Address address){	
		LOGGER.info("Address details are saved:"+address.toString());
		try {
				Address addrObj = addrService.getAddrByPhoneNum(address.getPhoneNumber());
				if (addrObj==null) {
					Integer addressId = addrService.saveAddress(address);
					return new ResponseEntity<String>("Saved with id "+addressId, HttpStatus.OK);	
				}
				throw new UserPostCustomException("Address already exists with phone number "+address.getPhoneNumber());
		} catch(HttpMessageNotReadableException e) {
			throw new UserPostCustomException("Please check the request payload and re-try");	
		}
					
	}
	
	@GetMapping
	public ResponseEntity<?> getAllAddresses() {
		LOGGER.info("Get all addresses:");
		try {
			List<Address> addrList = addrService.getAllAddresses();
			return new ResponseEntity<List<Address>>(addrList,HttpStatus.OK);

		}catch (Exception e) {
			throw new UserUpdateCustomException("Could not get the addresses list");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAddress(@PathVariable(required = true) Integer id) {
		LOGGER.info("Get address with id:"+id);
		if(addrService.isAddressExist(id)) {
			Address address = addrService.getAddress(id);
			return new ResponseEntity<Address>(address, HttpStatus.OK);
		}
		throw new UserGetCustomException("Address does not exist with id"+id);
	}

}
