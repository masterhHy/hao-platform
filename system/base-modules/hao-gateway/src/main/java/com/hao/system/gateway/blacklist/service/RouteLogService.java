package com.hao.system.gateway.blacklist.service;

import com.hao.common.utils.vo.QueryRequest;
import com.hao.system.gateway.blacklist.entity.RouteLog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RouteLogService {

    Flux<RouteLog> findAll();

    Mono<RouteLog> create(RouteLog routeLog);

    Flux<RouteLog> delete(String ids);

    Flux<RouteLog> findPages(QueryRequest request, RouteLog routeLog);

    Mono<Long> findCount(RouteLog routeLog);
}
