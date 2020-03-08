package com.hao.system.gateway.blacklist.mapper;

import com.hao.system.gateway.blacklist.entity.RouteLog;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RouteLogMapper extends ReactiveMongoRepository<RouteLog, String> {

    Flux<RouteLog> deleteByIdIn(String[] ids);
}
