package com.cg.gold.address.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.gold.address.entity.Address;
import com.cg.gold.address.exception.AddressException;
import com.cg.gold.address.repository.AddressRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<Address> getAllAddresses() {
		return addressRepository.findAll();
	}

	@Override
	public Address getAddressById(Integer addressId) throws AddressException {
		return addressRepository.findById(addressId)
				.orElseThrow(() -> new AddressException("AddressService.ADDRESS_NOT_FOUND"));
	}

	@Override
	public void addAddress(Address address) {
		addressRepository.save(address);
	}

	@Override
	public void updateAddressById(Integer addressId, Address updatedAddress) throws AddressException {
		Address existing = addressRepository.findById(addressId)
				.orElseThrow(() -> new AddressException("AddressService.ADDRESS_NOT_FOUND"));

		existing.setStreet(updatedAddress.getStreet());
		existing.setCity(updatedAddress.getCity());
		existing.setState(updatedAddress.getState());
		existing.setPostalCode(updatedAddress.getPostalCode());
		existing.setCountry(updatedAddress.getCountry());

		addressRepository.save(existing);
	}

}
