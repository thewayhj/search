package com.api.search;

import com.api.search.dto.VaultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(VaultDto.class)
public class SearchApplication implements CommandLineRunner {

	private final VaultDto vaultDto;

	public SearchApplication(VaultDto VaultDto) {
		this.vaultDto = VaultDto;
	}

	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("----------------------------------------");
		log.info("Configuration properties");
		log.info("   example.getKakaoRestApiKey is {}", vaultDto.getKakaoRestApiKey());
		log.info("   example.getNaverClientId is {}", vaultDto.getNaverClientId());
		log.info("   example.getNaverClientSecret is {}", vaultDto.getNaverClientSecret());
		log.info("----------------------------------------");
	}
}
