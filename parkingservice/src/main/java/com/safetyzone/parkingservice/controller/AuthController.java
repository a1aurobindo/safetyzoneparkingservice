package com.safetyzone.parkingservice.controller;

import com.safetyzone.parkingservice.domain.AuthDto;
import com.safetyzone.parkingservice.domain.AuthTokenDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

/**
 * Controller class to manage the redirect from the cognito auth server and token retrieval
 * @author Aurobindo.Parida
 * @since 06/21/2023
 */
@Tag(name = "Auth Controller")
@RestController
public class AuthController {

    private RestTemplate restTemplate;
    private ClientRegistrationRepository clientRegistration;

    public AuthController(RestTemplate restTemplate,
                          ClientRegistrationRepository clientRegistration) {

        this.restTemplate = restTemplate;
        this.clientRegistration = clientRegistration;
    }

    @GetMapping("login/oauth2/code/cognito")
    public AuthTokenDto cognitoCallBack(AuthDto authDto) {

        ClientRegistration client = clientRegistration.findByRegistrationId("cognito"); // Get client registration object

        /**
         * Prepare header for the auth token http call
         */
        String authHeader = client.getClientId() + ":" + client.getClientSecret();
        String base64 = new String(Base64.getEncoder().encode(authHeader.getBytes()));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + base64);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);

        /**
         * Prepare body for the auth token http call
         */
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("client_id", client.getClientId());
        map.add("redirect_uri", client.getRedirectUri());
        map.add("grant_type", client.getAuthorizationGrantType().getValue());
        map.add("code", authDto.getCode());

        HttpEntity<MultiValueMap> request = new HttpEntity<>(map, headers);
        ResponseEntity<AuthTokenDto> resp;
        resp = restTemplate.postForEntity(client.getProviderDetails().getTokenUri(),
            request, AuthTokenDto.class);

        return resp.getBody(); // return the token back
    }
}
