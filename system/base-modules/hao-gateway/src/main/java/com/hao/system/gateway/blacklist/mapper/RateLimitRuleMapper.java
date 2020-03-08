package com.hao.system.gateway.blacklist.mapper;

import com.hao.system.gateway.blacklist.entity.RateLimitRule;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RateLimitRuleMapper extends ReactiveMongoRepository<RateLimitRule, String> {

    Flux<RateLimitRule> deleteByIdIn(String[] ids);

    Flux<RateLimitRule> findByRequestUriAndRequestMethod(String requestUri, String requestMethod);
}
