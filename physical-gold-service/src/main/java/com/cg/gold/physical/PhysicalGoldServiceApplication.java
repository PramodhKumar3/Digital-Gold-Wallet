package com.cg.gold.physical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cg.gold.physical.repository")
@EntityScan(basePackages = "com.cg.gold.physical.entity")
@EnableFeignClients
public class PhysicalGoldServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhysicalGoldServiceApplication.class, args);
	}

}
