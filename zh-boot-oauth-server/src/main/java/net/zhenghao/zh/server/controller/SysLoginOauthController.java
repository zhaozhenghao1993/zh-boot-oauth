package net.zhenghao.zh.server.controller;

import net.zhenghao.zh.server.entity.SysAccessToken;
import net.zhenghao.zh.server.entity.SysAuthorizeEntity;
import net.zhenghao.zh.server.entity.SysClientEntity;
import net.zhenghao.zh.server.entity.SysUserEntity;
import net.zhenghao.zh.server.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

/**
 * 授权controller
 */
@Controller
@RequestMapping("/login/oauth/server")
public class SysLoginOauthController {

    //应用id
    String CLIENT_ID = "123456";
    //应用secret
    String CLIENT_SECRET = "4857106";

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/authorize", method = RequestMethod.GET)
    public ModelAndView getAuthorize(SysAuthorizeEntity authorize) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("authorize", authorize);
        mv.setViewName("oauth-login");
        return mv;
    }

    @RequestMapping(value = "/authorize", method = RequestMethod.POST)
    public ModelAndView postAuthorize(SysAuthorizeEntity authorize) throws OAuthSystemException {
        ModelAndView mv = new ModelAndView();
        SysUserEntity user = sysUserService.findUserByUsername(new SysUserEntity(authorize.getUsername(), authorize.getPassword()));
        if (user != null) {
            //设置授权码
            String authorizationCode;
            //利用oauth授权请求设置responseType，目前仅支持CODE，另外还有TOKEN

            //responseType目前仅支持CODE，另外还有TOKEN
            String responseType = authorize.getResponse_type();
            if (responseType.equals(ResponseType.CODE.toString())) {
                //生成授权码
                authorizationCode = UUID.randomUUID().toString();
                redisTemplate.opsForValue().set(authorizationCode, authorize.getUsername());
                StringBuffer sb = new StringBuffer();
                sb.append(authorize.getRedirect_uri());
                sb.append("?");
                sb.append("code=").append(authorizationCode).append("&");
                sb.append("state=").append(authorize.getState());
                String callbackUrl = sb.toString();
                System.out.println(callbackUrl);
                mv.setViewName("redirect:" + callbackUrl);
            }
        } else {
            mv.addObject("authorize", authorize);
            mv.addObject("error", "用户名密码错误或不存在!");
            mv.setViewName("oauth-login");
        }

        return mv;
    }

    @RequestMapping(value = "/access_token", method = RequestMethod.GET)
    @ResponseBody
    public SysAccessToken getAccessToken(SysClientEntity client) throws OAuthSystemException {
        SysAccessToken access_token = new SysAccessToken();
        //检查提交的客户端id是否正确
        if (!CLIENT_ID.equals(client.getClient_id())) {
            access_token.setAccess_token("");
            access_token.setError_msg("client_id不正确");
        } else if (!CLIENT_SECRET.equals(client.getClient_secret())  ) {
            // 检查客户端安全secret是否正确
            access_token.setAccess_token("");
            access_token.setError_msg("client_secret不正确");
        } else if (StringUtils.isBlank((String) redisTemplate.opsForValue().get(client.getCode()))) {
            // 检查code是否存在
            access_token.setAccess_token("");
            access_token.setError_msg("code不正确");
        } else {
            //生成Access Token
            OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
            final String accessToken = oauthIssuerImpl.accessToken();
            //保存Access Token和redis里面存的用户信息
            redisTemplate.opsForValue().set(accessToken, (String) redisTemplate.opsForValue().get(client.getCode()));
            //删除code 一个code只用一次
            redisTemplate.delete(client.getCode());

            access_token.setAccess_token(accessToken);
            access_token.setError_msg("success");
        }
        return access_token;
    }
}
