package com.cg.gold.vendorbranch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cg.gold.vendorbranch.repository")
@EntityScan(basePackages = "com.cg.gold.vendorbranch.entity")
@EnableFeignClients
public class VendorBranchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendorBranchServiceApplication.class, args);
	}

}
