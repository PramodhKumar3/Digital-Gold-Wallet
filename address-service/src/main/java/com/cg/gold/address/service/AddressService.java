package com.cg.gold.address.service;

import java.util.List;

import com.cg.gold.address.entity.Address;
import com.cg.gold.address.exception.AddressException;

public interface AddressService {

	public List<Address> getAllAddresses();

	public Address getAddressById(Integer addressId) throws AddressException;

	public void addAddress(Address address);

	public void updateAddressById(Integer addressId, Address updatedAddress) throws AddressException;
}
