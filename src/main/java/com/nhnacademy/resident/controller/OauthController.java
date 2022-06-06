package com.nhnacademy.resident.controller;

import com.nhnacademy.resident.domain.Token;
import com.nhnacademy.resident.domain.UserResponse;
import com.nhnacademy.resident.service.OauthLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;



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
        boolean loginSuccess = oauthLoginService.login(userResponse.getEmail());

        if (!loginSuccess) return "redirect:/logout";
        return "redirect:/";
    }

}
