package com.hng.service;

import com.hng.dto.CatFactResponseDto;
import com.hng.dto.ProfileResponseDto;
import com.hng.dto.UserInfo;
import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

@Service
public class ProfileService {
    private static final Logger logger = LoggerFactory.getLogger(ProfileService.class);
    private static final String CAT_FACT_API = "https://catfact.ninja/fact";

    private final RestTemplate restTemplate = new RestTemplate();

    public ProfileResponseDto getProfileWithCatFact() {
        // Fetch cat fact from external API
        String catFact = fetchCatFact();

        // Build user info
        UserInfo userInfo = new UserInfo();
        userInfo.setName("Lavenda");
        userInfo.setEmail("oderolavenda@mail.com");
        userInfo.setStack("Backend Development (Java/Spring Boot)");

        // Build response with current timestamp
        ProfileResponseDto response = new ProfileResponseDto();
        response.setStatus("success");
        response.setUser(userInfo);
        response.setFact(catFact);
        response.setTimestamp(Instant.now().toString());

        return response;

    }

    private String fetchCatFact() {
        try {
            logger.info("Fetching cat fact from external API");
            CatFactResponseDto response = restTemplate.getForObject(
                    CAT_FACT_API,
                    CatFactResponseDto.class
            );

            if (response != null && response.getFact() != null) {
                logger.info("Successfully fetched cat fact");
                return response.getFact();
            }

            throw new RuntimeException("Cat fact API returned null response");

        } catch (RestClientException e) {
            logger.error("Failed to fetch cat fact: {}", e.getMessage());
            throw new RuntimeException("External API unavailable", e);
        }
    }
}
