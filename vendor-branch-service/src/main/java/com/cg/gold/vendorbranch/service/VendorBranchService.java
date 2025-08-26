package com.cg.gold.vendorbranch.service;

import java.util.List;

import com.cg.gold.vendorbranch.dto.TransactionHistoryDTO;
import com.cg.gold.vendorbranch.entity.VendorBranch;
import com.cg.gold.vendorbranch.exception.VendorBranchException;

public interface VendorBranchService {
	public List<VendorBranch> getAllVendorBranches();

	public VendorBranch getVendorBranchByBranchId(Integer branchId) throws VendorBranchException;

	public List<VendorBranch> getVendorBranchesByVendorId(Integer vendorId) throws VendorBranchException;

	public List<VendorBranch> getVendorBranchesByCity(String city) throws VendorBranchException;

	public List<VendorBranch> getVendorBranchesByState(String state) throws VendorBranchException;

	public List<VendorBranch> getVendorBranchesByCountry(String country) throws VendorBranchException;

	public List<TransactionHistoryDTO> getVendorBranchTransactionsByBranchId(Integer branchId)
			throws VendorBranchException;

	public void addBranch(VendorBranch newVendorBranch) throws VendorBranchException;

	public void updateBranch(Integer branchId, VendorBranch updatedBranch) throws VendorBranchException;

	public void transferGold(Integer sourceBranchId, Integer destinationBranchId, Double quantity)
			throws VendorBranchException;
}
