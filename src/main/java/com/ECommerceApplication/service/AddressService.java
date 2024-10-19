package com.ECommerceApplication.service;

import com.ECommerceApplication.dto.AddressDTO;
import com.ECommerceApplication.entity.Address;

import java.util.List;

public interface AddressService {
	
	AddressDTO createAddress(AddressDTO addressDTO);
	
	List<AddressDTO> getAddresses();
	
	AddressDTO getAddress(Long addressId);
	
	AddressDTO updateAddress(Long addressId, Address address);
	
	String deleteAddress(Long addressId);
}