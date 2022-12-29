package com.api.search.dto;

import lombok.Setter;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties("local")
public class VaultDto {
    private String kakaoRestApiKey;
    private String naverClientId;
    private String naverClientSecret;
}
