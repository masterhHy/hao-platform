package com.hao.system.gateway.blacklist.service;

import com.hao.common.utils.vo.QueryRequest;
import com.hao.system.gateway.blacklist.entity.RateLimitRule;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RateLimitRuleService {

    Mono<RateLimitRule> create(RateLimitRule rateLimitRule);

    Flux<RateLimitRule> findAll();

    Flux<RateLimitRule> findByRequestUriAndRequestMethod(String requestUri, String requestMethod);

    Flux<RateLimitRule> findPages(QueryRequest request, RateLimitRule rateLimitRule);

    Mono<Long> findCount(RateLimitRule rateLimitRule);

    Mono<RateLimitRule> update(RateLimitRule rateLimitRule);

    Flux<RateLimitRule> delete(String ids);
}
