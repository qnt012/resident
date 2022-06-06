package com.nhnacademy.resident.service;

import com.nhnacademy.resident.domain.Token;
import com.nhnacademy.resident.domain.UserResponse;

public interface OauthLoginService {
    Token getAccessToken(String clientId, String clientSecret, String code);
    UserResponse getUser(String accessToken);
    boolean login(String email);
}
