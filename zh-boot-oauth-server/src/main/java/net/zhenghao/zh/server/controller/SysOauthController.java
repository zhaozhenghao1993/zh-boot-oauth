package net.zhenghao.zh.server.controller;

import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 授权controller
 */
@Controller
@RequestMapping("/oauth/server")
public class SysOauthController {

    //应用id
    String appId = "c1ebe466-1cdc-4bd3-ab69-77c3561b9dee";
    //应用secret
    String appSecret = "d8346ea2-6017-43ed-ad68-19c0f971738b";

    @RequestMapping("/authorize")
    public void authorize(HttpServletRequest request) throws OAuthProblemException, OAuthSystemException {
        //构建OAuth授权请求
        OAuthAuthzRequest oauthRequest =new OAuthAuthzRequest(request);
    }
}
