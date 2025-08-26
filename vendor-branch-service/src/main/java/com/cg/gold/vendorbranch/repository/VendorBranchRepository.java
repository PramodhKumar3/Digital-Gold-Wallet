package com.cg.gold.vendorbranch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.gold.vendorbranch.entity.VendorBranch;

public interface VendorBranchRepository extends JpaRepository<VendorBranch, Integer> {

	List<VendorBranch> findByVendorVendorId(Integer vendorId);

	List<VendorBranch> findByAddressCityIgnoreCase(String city);

	List<VendorBranch> findByAddressStateIgnoreCase(String state);

	List<VendorBranch> findByAddressCountryIgnoreCase(String country);

}
