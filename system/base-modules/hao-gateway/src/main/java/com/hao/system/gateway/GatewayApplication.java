package com.hao.system.gateway;

import com.hao.common.annotation.EnableLettuceRedis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@Slf4j
@EnableLettuceRedis
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
        log.info("  _   _   _   _   _   _   _");
        log.info(" / \\ / \\ / \\ / \\ / \\ / \\ / \\");
        log.info("| s | t | a | r | t | e | d |");
        log.info(" \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/");
        log.info("启动完毕，时间：{}", LocalDateTime.now());
    }


}
