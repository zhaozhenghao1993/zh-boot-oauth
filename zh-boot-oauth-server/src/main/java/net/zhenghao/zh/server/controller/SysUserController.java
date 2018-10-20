package net.zhenghao.zh.server.controller;

import net.zhenghao.zh.server.entity.SysUserEntity;
import net.zhenghao.zh.server.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/listUser")
    public List<SysUserEntity> listUser() {
        return sysUserService.listUser();
    }

    @RequestMapping("/api/userinfo")
    @ResponseBody
    public SysUserEntity userinfo(String access_token) {
        String username = (String) redisTemplate.opsForValue().get(access_token);
        return sysUserService.getUserByUsername(username);
    }
}
