package com.hao.system.gateway.blacklist.service.impl;

import com.hao.common.utils.AddressUtil;
import com.hao.common.utils.DateHelper;
import com.hao.common.utils.vo.QueryRequest;
import com.hao.system.gateway.blacklist.entity.BlackList;
import com.hao.system.gateway.blacklist.mapper.BlackListMapper;
import com.hao.system.gateway.blacklist.service.BlackListService;
import com.hao.system.gateway.blacklist.service.RouteEnhanceCacheService;
import com.hao.system.gateway.blacklist.utils.PageableExecutionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class BlackListServiceImpl implements BlackListService {

    @Autowired
    private BlackListMapper blackListMapper;
    @Autowired
    private ReactiveMongoTemplate template;
    @Autowired
    private RouteEnhanceCacheService routeEnhanceCacheService;

    @Override
    public Flux<BlackList> findAll() {
        return blackListMapper.findAll();
    }

    @Override
    public Mono<BlackList> create(BlackList blackList) {
        blackList.setCreateTime(DateHelper.getSystemTime(DateHelper.DEFAULT_DATETIME_FORMAT));
        if (StringUtils.isNotBlank(blackList.getIp()))
            blackList.setLocation(AddressUtil.getCityInfo(blackList.getIp()));
        return blackListMapper.insert(blackList).doOnSuccess(b -> routeEnhanceCacheService.saveBlackList(blackList));
    }

    @Override
    public Mono<BlackList> update(BlackList blackList) {
        return this.blackListMapper.findById(blackList.getId())
                .flatMap(b -> {
                    routeEnhanceCacheService.removeBlackList(b);
                    BeanUtils.copyProperties(blackList, b);
                    return this.blackListMapper.save(b);
                }).doOnSuccess(b -> routeEnhanceCacheService.saveBlackList(b));
    }

    @Override
    public Flux<BlackList> delete(String ids) {
        String[] idArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(ids, ",");
        return blackListMapper.deleteByIdIn(idArray)
                .doOnNext(b -> routeEnhanceCacheService.removeBlackList(b));
    }

    @Override
    public Flux<BlackList> findPages(QueryRequest request, BlackList blackList) {
        Query query = getQuery(blackList);
        return PageableExecutionUtil.getPages(query, request, BlackList.class, template);
    }

    @Override
    public Mono<Long> findCount(BlackList blackList) {
        Query query = getQuery(blackList);
        return template.count(query, BlackList.class);
    }

    @Override
    public Flux<BlackList> findByCondition(String ip, String requestUri, String requestMethod) {
        if (StringUtils.isBlank(ip)) {
            return blackListMapper.findByRequestUriAndRequestMethod(requestUri, requestMethod);
        }
        return blackListMapper.findByIpAndRequestUriAndRequestMethod(ip, requestUri, requestMethod);
    }

    private Query getQuery(BlackList blackList) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (StringUtils.isNotBlank(blackList.getIp())) {
            criteria.and("ip").is(blackList.getIp());
        }
        if (StringUtils.isNotBlank(blackList.getRequestUri())) {
            criteria.and("requestUri").is(blackList.getRequestUri());
        }
        if (StringUtils.isNotBlank(blackList.getRequestMethod())) {
            criteria.and("requestMethod").is(blackList.getRequestMethod());
        }
        if (StringUtils.isNotBlank(blackList.getStatus())) {
            criteria.and("status").is(blackList.getStatus());
        }
        query.addCriteria(criteria);
        return query;
    }
}
