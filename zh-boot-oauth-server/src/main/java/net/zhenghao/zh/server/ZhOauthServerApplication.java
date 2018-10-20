package net.zhenghao.zh.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("net.zhenghao.zh.server.dao")
public class ZhOauthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhOauthServerApplication.class, args);
    }
}
