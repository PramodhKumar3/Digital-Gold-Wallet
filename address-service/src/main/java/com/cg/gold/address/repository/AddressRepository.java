package com.cg.gold.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.gold.address.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
