package com.cg.gold.transaction.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.gold.transaction.entity.VendorBranch;

@FeignClient(name = "vendor-branch-service", url = "http://localhost:8104")
public interface VendorBranchFeignClient {

	@GetMapping("/api/v2/vendor_branches")
	public List<VendorBranch> getAllBranches();

	@GetMapping("/api/v2/vendor_branches/{branch_id}")
	public VendorBranch getBranchById(@PathVariable("branch_id") Integer branchId);
}
