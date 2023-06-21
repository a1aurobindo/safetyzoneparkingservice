package com.safetyzone.parkingservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Aurobindo.Parida
 * @since 06/21/2023
 */
@Configuration
public class WebConfig {

    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }
}
