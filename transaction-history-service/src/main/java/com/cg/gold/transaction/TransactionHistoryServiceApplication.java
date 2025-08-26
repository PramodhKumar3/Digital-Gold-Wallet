package com.cg.gold.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cg.gold.transaction.repository")
@EntityScan(basePackages = "com.cg.gold.transaction.entity")
@EnableFeignClients
public class TransactionHistoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionHistoryServiceApplication.class, args);
	}

}
