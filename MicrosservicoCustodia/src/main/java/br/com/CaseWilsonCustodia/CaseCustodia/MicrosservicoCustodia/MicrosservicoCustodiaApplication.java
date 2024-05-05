package br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicrosservicoCustodiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrosservicoCustodiaApplication.class, args);
	}

}
