package com.cg.gold.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.gold.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByName(String name);

	List<User> findByAddressCity(String city);

	List<User> findByAddressState(String state);

}
