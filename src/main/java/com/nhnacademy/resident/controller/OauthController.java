package com.nhnacademy.resident.controller;

import com.nhnacademy.resident.domain.Token;
import com.nhnacademy.resident.domain.UserResponse;
import com.nhnacademy.resident.entity.Resident;
import com.nhnacademy.resident.service.OauthLoginService;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Controller
@Slf4j
public class OauthController {
    private final OauthLoginService oauthLoginService;

    public OauthController(OauthLoginService oauthLoginService) {
        this.oauthLoginService = oauthLoginService;
    }

    @GetMapping("oauth/github")
    public String getOauthGithub() {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("github.com")
                .path("/login/oauth/authorize")
                .queryParam("client_id", "9d689d45783c85dc4da3")
                .build();
        return "redirect:"+uriComponents;
    }


    @GetMapping("/login/oauth2/code/github")
    public String getOauthGithubCode(@RequestParam String code) {
        Token accessToken = oauthLoginService.getAccessToken("9d689d45783c85dc4da3", "cbb56a9557586fd0c27036d060abd221e415bac4", code);
        UserResponse userResponse = oauthLoginService.getUser(accessToken.getAccess_token());
        oauthLoginService.login(userResponse.getEmail());

        return "redirect:/";
    }

}
