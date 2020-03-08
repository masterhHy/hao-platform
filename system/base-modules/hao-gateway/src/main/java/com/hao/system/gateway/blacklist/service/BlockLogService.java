package com.hao.system.gateway.blacklist.service;

import com.hao.common.utils.vo.QueryRequest;
import com.hao.system.gateway.blacklist.entity.BlockLog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BlockLogService {

    Mono<BlockLog> create(BlockLog blockLog);

    Flux<BlockLog> delete(String ids);

    Flux<BlockLog> findPages(QueryRequest request, BlockLog blockLog);

    Mono<Long> findCount(BlockLog blockLog);
}
