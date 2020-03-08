package com.hao.system.gateway.blacklist.service;

import com.hao.common.constant.SystemConstant;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public interface RouteEnhanceService {

    Mono<Void> filterBalckList(ServerWebExchange exchange);

    Mono<Void> filterRateLimit(ServerWebExchange exchange);

    @Async(SystemConstant.ASYNC_POOL)
    void saveRequestLogs(ServerWebExchange exchange);

    @Async(SystemConstant.ASYNC_POOL)
    void saveBlockLogs(ServerWebExchange exchange);

    @Async(SystemConstant.ASYNC_POOL)
    void saveRateLimitLogs(ServerWebExchange exchange);
}
