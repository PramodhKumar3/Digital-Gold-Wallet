package com.cg.gold.apigatewayserver;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	RouteLocator gatewayRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("goldwallet_prefixed",
						r -> r.path("/goldwalletapplication/**")
								.filters(f -> f.rewritePath("/goldwalletapplication/(?<segment>.*)", "/${segment}"))
								.uri("lb://goldwalletapplication"))

				.route("static_resources", r -> r.path("/images/**").uri("lb://goldwalletapplication"))
				.route("goldwallet_direct", r -> r.path("/api/v3/**").uri("lb://goldwalletapplication"))
				.route("goldwallet_direct", r -> r.path("/api/v1/users/**").uri("lb://goldwalletapplication"))
				.route("goldwallet_direct", r -> r.path("/api/v1/address/**").uri("lb://goldwalletapplication"))
				.route("goldwallet_direct", r -> r.path("/api/v1/vendor/**").uri("lb://goldwalletapplication"))
				.route("goldwallet_direct", r -> r.path("/api/v1/vendor_branches/**").uri("lb://goldwalletapplication"))
				.route("goldwallet_direct",
						r -> r.path("/api/v1/virtual_gold_holding/**").uri("lb://goldwalletapplication"))
				.route("goldwallet_direct",
						r -> r.path("/api/v1/physical_gold_transactions/**").uri("lb://goldwalletapplication"))
				.route("goldwallet_direct", r -> r.path("/api/v1/payments/**").uri("lb://goldwalletapplication"))
				.route("goldwallet_direct",
						r -> r.path("/api/v1/transaction_history/**").uri("lb://goldwalletapplication"))
				.build();

	}
}
