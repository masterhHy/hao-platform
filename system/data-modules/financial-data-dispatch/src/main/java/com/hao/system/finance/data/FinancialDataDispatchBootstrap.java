package com.hao.system.finance.data;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@SpringBootApplication
public class FinancialDataDispatchBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(FinancialDataDispatchBootstrap.class, args);
        log.info("  _   _   _   _   _   _   _");
        log.info(" / \\ / \\ / \\ / \\ / \\ / \\ / \\");
        log.info("| s | t | a | r | t | e | d |");
        log.info(" \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/");
        log.info("启动完毕，时间：{}", LocalDateTime.now());
    }

}
