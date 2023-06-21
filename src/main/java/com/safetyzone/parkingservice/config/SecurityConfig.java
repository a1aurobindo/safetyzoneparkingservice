package com.safetyzone.parkingservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Security class for Oauth client and resource server
 * @author Aurobindo.Parida
 * @since 6/21/2023
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Order(1)
    @Bean
    public SecurityFilterChain clientFilterChain(HttpSecurity http) throws Exception {

        http.requestMatchers().antMatchers("/token", "/oauth2/authorization/cognito")
            .and()
            .authorizeHttpRequests(requests -> requests.anyRequest().authenticated())
            .cors().and()
            .csrf(withDefaults())
            .oauth2Login()
            .and()
            .logout()
            .logoutSuccessUrl("/");
        return http.build();
    }


    @Order(2)
    @Bean
    public SecurityFilterChain resourceFilterChain(HttpSecurity http) throws Exception {

        http.antMatcher("/parking/*").authorizeHttpRequests(requests -> requests.requestMatchers(
                new RequestHeaderRequestMatcher("Authorization")).authenticated())
            .cors().and()
            .csrf(withDefaults())
            .oauth2ResourceServer(resourceServer -> resourceServer.jwt());
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH", "PUT", "DELETE", "OPTIONS", "HEAD"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Access-Control-Allow-Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Origin", "Cache-Control", "Content-Type", "Authorization"));
        configuration.setExposedHeaders(Arrays.asList("X-Get-Header"));
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
