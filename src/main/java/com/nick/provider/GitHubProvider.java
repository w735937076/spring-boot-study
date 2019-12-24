package com.nick.provider;

import com.alibaba.fastjson.JSON;
import com.nick.vo.AccessTokenVO;
import com.nick.vo.GitHubUserVO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: WZL
 * @Date: 2019/12/24 00:14
 * @Component 初始化到Spring的上下文(IOC)
 */
@Component
public class GitHubProvider {
    public String getAccessToken(AccessTokenVO accessTokenVO) throws IOException {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenVO));
        Request request = new Request.Builder().url("https://github.com/login/oauth/access_token").post(body).build();
        Response response = client.newCall(request).execute();
        String str = response.body().string();
        String token = str.split("&")[0].split("=")[1];
        System.out.println("返回值=="+str);
        System.out.println("token=="+token);
        return token;
    }

    public GitHubUserVO gitHubUserByAccessToken(String accessToken) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.github.com/user?access_token="+accessToken).build();

        Response response = client.newCall(request).execute();
        String str = response.body().string();
        GitHubUserVO gitHubUserVO = JSON.parseObject(str, GitHubUserVO.class);

        return gitHubUserVO;
    }
}
