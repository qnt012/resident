package com.nhnacademy.resident.service.impl;

import com.nhnacademy.resident.domain.Token;
import com.nhnacademy.resident.domain.UserResponse;
import com.nhnacademy.resident.entity.Resident;
import com.nhnacademy.resident.repository.ResidentRepository;
import com.nhnacademy.resident.service.OauthLoginService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GithubOauthLoginService implements OauthLoginService {
    private final RestTemplate restTemplate;
    private final ResidentRepository residentRepository;

    public GithubOauthLoginService(RestTemplate restTemplate, ResidentRepository residentRepository) {
        this.restTemplate = restTemplate;
        this.residentRepository = residentRepository;
    }


    @Override
    public Token getAccessToken(String clientId, String clientSecret, String code) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("github.com")
                .path("/login/oauth/access_token")
                .queryParam("client_id","9d689d45783c85dc4da3" )
                .queryParam("client_secret", "cbb56a9557586fd0c27036d060abd221e415bac4")
                .queryParam("code", code)
                .build();

        ResponseEntity<Token> response = restTemplate.exchange(
                uriComponents.toUri(),
                HttpMethod.POST,
                null,
                Token.class
        );
        return response.getBody();
    }

    @Override
    public UserResponse getUser(String accessToken) {
        UriComponents userUriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.github.com")
                .path("/user")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Token "+accessToken);
        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<UserResponse> userResponse = restTemplate.exchange(
                userUriComponents.toUri(),
                HttpMethod.GET,
                httpEntity,
                UserResponse.class
        );
        return userResponse.getBody();
    }

    @Override
    public boolean login(String email) {
        Optional<Resident> resident = residentRepository.findByEmail(email);

        if (resident.isPresent()){
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(resident.get().getAuthority().getAuthority());
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(simpleGrantedAuthority);
            UserDetails userDetails = new User(resident.get().getResidentId(), resident.get().getPwd(),
                    Collections.singletonList(simpleGrantedAuthority));
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "USER_PASSWORD", authorities);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            return true;
        }

        return false;
    }
}
