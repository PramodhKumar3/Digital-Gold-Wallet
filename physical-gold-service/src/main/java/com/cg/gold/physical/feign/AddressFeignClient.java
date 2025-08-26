package com.cg.gold.physical.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.gold.physical.entity.Address;

@FeignClient(name = "address-service", url = "http://localhost:8102")
public interface AddressFeignClient {

	@GetMapping("/api/v2/address")
	public List<Address> getAllAddresses();

	@GetMapping("/api/v2/address/{address_id}")
	public Address getAddressById(@PathVariable("address_id") Integer addressId);
}
