package com.hao.system.gateway.blacklist.mapper;

import com.hao.system.gateway.blacklist.entity.RateLimitLog;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RateLimitLogMapper extends ReactiveMongoRepository<RateLimitLog, String> {

    Flux<RateLimitLog> deleteByIdIn(String[] ids);
}
