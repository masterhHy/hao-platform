package com.hao.system.gateway.blacklist.mapper;

import com.hao.system.gateway.blacklist.entity.BlackList;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BlackListMapper extends ReactiveMongoRepository<BlackList, String> {

    Flux<BlackList> deleteByIdIn(String[] ids);

    Flux<BlackList> findByIpAndRequestUriAndRequestMethod(String ip, String requestUri, String requestMethod);

    Flux<BlackList> findByRequestUriAndRequestMethod(String requestUri, String requestMethod);

}
