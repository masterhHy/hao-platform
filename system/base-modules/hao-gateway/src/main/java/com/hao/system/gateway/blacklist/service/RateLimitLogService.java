package com.hao.system.gateway.blacklist.service;

import com.hao.common.utils.vo.QueryRequest;
import com.hao.system.gateway.blacklist.entity.RateLimitLog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RateLimitLogService {
    Mono<RateLimitLog> create(RateLimitLog rateLimitLog);

    Flux<RateLimitLog> delete(String ids);

    Flux<RateLimitLog> findPages(QueryRequest request, RateLimitLog rateLimitLog);

    Mono<Long> findCount(RateLimitLog rateLimitLog);
}
