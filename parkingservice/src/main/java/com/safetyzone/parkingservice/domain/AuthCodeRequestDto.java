package com.safetyzone.parkingservice.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuthCodeRequestDto {
    String grantType;
    String clientId;
    String clientSecret;
    String redirectUri;
    String code;
}
