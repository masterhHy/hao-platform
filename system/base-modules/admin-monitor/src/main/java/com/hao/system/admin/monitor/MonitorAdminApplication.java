package com.hao.system.admin.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@EnableAdminServer
@SpringBootApplication
public class MonitorAdminApplication {

    private static Logger log = LoggerFactory.getLogger(MonitorAdminApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(MonitorAdminApplication.class, args);
        log.info("  _   _   _   _   _   _   _");
        log.info(" / \\ / \\ / \\ / \\ / \\ / \\ / \\");
        log.info("| s | t | a | r | t | e | d |");
        log.info(" \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/");
        log.info("启动完毕，时间：{}", LocalDateTime.now());
    }

}
