package net.zhenghao.zh.client.controller;

import net.zhenghao.zh.client.entity.SysAccessToken;
import net.zhenghao.zh.client.entity.SysUserEntity;
import net.zhenghao.zh.common.utils.HttpClientUtils;
import net.zhenghao.zh.common.utils.JSONUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * 登陆授权controller
 */
@Controller
@RequestMapping("/login/oauth/client")
public class SysLoginOauthController {

    //应用id
    String CLIENT_ID = "123456";
    //应用secret
    String CLIENT_SECRET = "4857106";

    //String STATE = "4857106";

    String REDIRECT_URL = "http://127.0.0.1:8080/login/oauth/client/callback";

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/sign")
    public String sign(String ref_url) throws IOException, OAuthSystemException {

        //把哪个页面登陆的地址扔到redis,key值为STATE,回调时使用
        String STATE = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(STATE, ref_url);

        // server认证服务器地址
        String url = "http://localhost:9090/login/oauth/server/authorize";
        // 生成并保存state，忽略该参数有可能导致CSRF攻击
        // 传递参数response_type、client_id、state、redirect_uri
        String param = "response_type=code&" + "client_id=" + CLIENT_ID + "&state=" + STATE
                + "&redirect_uri=" + REDIRECT_URL;

        // 1、请求server认证服务器
        return "redirect:" + url + "?" + param;
    }

    @RequestMapping("/callback")
    public ModelAndView callback(String code, String state, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        // 验证state，判断redis中是否存在此state，可能被CSRF攻击
        String ref_url = (String) redisTemplate.opsForValue().get(state);
        if(StringUtils.isBlank(ref_url)) {
            throw new Exception("State验证失败");
        }
        System.out.println(code);

        // 2、向认证服务器申请令牌
        String url = "http://localhost:9090/login/oauth/server/access_token";
        // 传递参数grant_type、code、redirect_uri、client_id
        String param = "?code=" + code + "&redirect_uri=" +
                REDIRECT_URL + "&client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET;

        // 申请令牌，注意此处为post请求
        String result = HttpClientUtils.sendGet(url + param);
        SysAccessToken access_token = (SysAccessToken) JSONUtils.jsonToBean(result, new SysAccessToken());

        System.out.println(access_token.getAccess_token());
        System.out.println(access_token.getError_msg());


        // 3、用令牌向认证服务器申请用户资源
        String url_userinfo = "http://localhost:9090/user/api/userinfo?access_token=" + access_token.getAccess_token();

        String result_userinfo = HttpClientUtils.sendGet(url_userinfo);
        SysUserEntity user = (SysUserEntity) JSONUtils.jsonToBean(result_userinfo, new SysUserEntity());

        System.out.println("userinfo===>" + JSONUtils.beanToJson(user));
        System.out.println("ref_url===>" + ref_url);
        request.getSession().setAttribute("user", user);
        //mv.addObject("user", user);
        mv.setViewName("redirect:" + ref_url);
        return mv;
    }
}
