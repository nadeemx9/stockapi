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
				.baseUrl("https://apidojo-yahoo-finance-v1.p.rapidapi.com")
				.defaultHeaders(httpHeaders -> {
					httpHeaders.add("X-RapidAPI-Key", "0d571fbef7msh992d7843df4fbfcp14faadjsn13cac5bc4ed4");
					httpHeaders.add("X-RapidAPI-Host","apidojo-yahoo-finance-v1.p.rapidapi.com");
				})
				.build();
	}
}
