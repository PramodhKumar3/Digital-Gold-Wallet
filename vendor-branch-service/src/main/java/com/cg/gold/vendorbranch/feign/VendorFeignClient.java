package com.cg.gold.vendorbranch.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.gold.vendorbranch.entity.Vendor;

@FeignClient(name = "vendor-service", url = "http://localhost:8103")
public interface VendorFeignClient {

	@GetMapping("/api/v2/vendor")
	public List<Vendor> getAllVendors();

	@GetMapping("/api/v2/vendor/{vendor_id}")
	public Vendor getVendorById(@PathVariable("vendor_id") Integer vendorId);
}
