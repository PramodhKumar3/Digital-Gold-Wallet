package com.cg.gold.virtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cg.gold.virtual.repository")
@EntityScan(basePackages = "com.cg.gold.virtual.entity")
@EnableFeignClients
public class VirtualGoldServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualGoldServiceApplication.class, args);
	}

}
