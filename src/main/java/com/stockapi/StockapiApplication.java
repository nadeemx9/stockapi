package com.stockapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class StockapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockapiApplication.class, args);
	}


	@Bean
	public RestClient restClient(){
		return RestClient.builder()
				.baseUrl("https://api-v2.upstox.com")
				.defaultHeaders(httpHeaders -> {
					httpHeaders.add("Api-Version", "2.0");
					httpHeaders.add("accept","application/json");
				})
				.build();

	}
}
