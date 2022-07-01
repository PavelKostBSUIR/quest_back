package com.softarex.api.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

    //todo where is it used?
    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;
    private Long userId;
}
