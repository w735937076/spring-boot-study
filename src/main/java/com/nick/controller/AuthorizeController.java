package com.nick.controller;

import com.nick.provider.GitHubProvider;
import com.nick.vo.AccessTokenVO;
import com.nick.vo.GitHubUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

/**
 * @Author: WZL
 * @Date: 2019/12/24 00:05
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client_id}")
    private String client_id;
    @Value("${github.client_secret}")
    private String client_secret;
    @Value("${github.redirect_uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,@RequestParam(name = "state") String state) throws IOException {
        AccessTokenVO accessTokenVO = new AccessTokenVO();
        accessTokenVO.setCode(code);
        accessTokenVO.setRedirect_uri(redirect_uri);
        accessTokenVO.setState(state);
        accessTokenVO.setClient_id(client_id);
        accessTokenVO.setClient_secret(client_secret);
        String access_token = gitHubProvider.getAccessToken(accessTokenVO);
        GitHubUserVO gitHubUserVO = gitHubProvider.gitHubUserByAccessToken(access_token);
        System.out.println(gitHubUserVO.getName());//验证是不是等于nick-console-wzl
        return  "index";
    }


}
