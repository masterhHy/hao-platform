package com.hao.system.gateway.blacklist.domain;

import com.hao.system.gateway.blacklist.service.BlackListService;
import com.hao.system.gateway.blacklist.service.RateLimitRuleService;
import com.hao.system.gateway.blacklist.service.RouteEnhanceCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RouteEnhanceRunner implements ApplicationRunner {

    @Autowired
    private RouteEnhanceCacheService cacheService;
    @Autowired
    private BlackListService blackListService;
    @Autowired
    private RateLimitRuleService rateLimitRuleService;

    @Override
    public void run(ApplicationArguments args) {
    	log.debug("初始化数据");
        cacheService.saveAllBlackList(blackListService.findAll());
        cacheService.saveAllRateLimitRules(rateLimitRuleService.findAll());
        log.debug("初始化数据完毕");
    }
}
