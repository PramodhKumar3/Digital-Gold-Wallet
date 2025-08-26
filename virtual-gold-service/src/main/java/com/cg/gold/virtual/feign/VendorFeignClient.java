package com.cg.gold.virtual.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.gold.virtual.dto.VendorDTO;

@FeignClient(name = "vendor-service", url = "http://localhost:8103")
public interface VendorFeignClient {

	@GetMapping("/api/v2/vendor")
	public List<VendorDTO> getAllVendors();

	@GetMapping("/api/v2/vendor/{vendor_id}")
	public VendorDTO getVendorById(@PathVariable("vendor_id") Integer vendorId);
}
