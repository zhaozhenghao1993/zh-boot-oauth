package net.zhenghao.zh.client.controller;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权controller
 */
@Controller
@RequestMapping("/oauth/client")
public class SysOauthController {

    //应用id
    String CLIENT_ID = "123456";
    //应用secret
    String CLIENT_SECRET = "4857106";

    String REDIRECT_URL = "http://127.0.0.1:8080/oauth/client/callback";

    @RequestMapping("/sign")
    public String sign() throws IOException, OAuthSystemException {

        String accessTokenUrl = "authorize";
        String response_type = "code";
        // server认证服务器地址
        String redirectUrl = "http://localhost:9090/oauth/server/authorize";

        OAuthClient oAuthClient =new OAuthClient(new URLConnectionClient());
        //构建oauthd的请求。设置请求服务地址（accessTokenUrl）、clientId、response_type、redirectUrl
        OAuthClientRequest accessTokenRequest = OAuthClientRequest
                .authorizationLocation(accessTokenUrl)
                .setResponseType(response_type)
                .setClientId(CLIENT_ID)
                .setRedirectURI(redirectUrl)
                .buildQueryMessage();

        String requestUrl = accessTokenRequest.getLocationUri();
        System.out.println(requestUrl);
        return "redirect:http://localhost:9090/oauth/server/"+requestUrl;
    }
}
