package com.nhnacademy.resident.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@Controller
@Slf4j
public class OauthController {
    private final RestTemplate restTemplate;

    public OauthController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("github.com")
                .path("/login/oauth/access_token")
                .queryParam("client_id","9d689d45783c85dc4da3" )
                .queryParam("client_secret", "cbb56a9557586fd0c27036d060abd221e415bac4")
                .queryParam("code", code)
                .build();

        ResponseEntity<String> response = restTemplate.exchange(
                uriComponents.toUri(),
                HttpMethod.POST,
                HttpEntity.EMPTY,
                String.class
        );

        log.error(response.getBody());

        return "redirect:/";
    }

}
