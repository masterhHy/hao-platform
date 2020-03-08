package com.hao.system.gateway.blacklist.service.impl;

import com.hao.common.utils.DateHelper;
import com.hao.common.utils.vo.QueryRequest;
import com.hao.system.gateway.blacklist.entity.RateLimitRule;
import com.hao.system.gateway.blacklist.mapper.RateLimitRuleMapper;
import com.hao.system.gateway.blacklist.service.RateLimitRuleService;
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
public class RateLimitRuleServiceImpl implements RateLimitRuleService {

    @Autowired
    private RateLimitRuleMapper rateLimitRuleMapper;
    @Autowired
    private ReactiveMongoTemplate template;
    @Autowired
    private RouteEnhanceCacheService routeEnhanceCacheService;


    @Override
    public Flux<RateLimitRule> findAll() {
        return rateLimitRuleMapper.findAll();
    }

    @Override
    public Flux<RateLimitRule> findByRequestUriAndRequestMethod(String requestUri, String requestMethod) {
        return rateLimitRuleMapper.findByRequestUriAndRequestMethod(requestUri, requestMethod);
    }

    @Override
    public Flux<RateLimitRule> findPages(QueryRequest request, RateLimitRule rateLimitRule) {
        Query query = getQuery(rateLimitRule);
        return PageableExecutionUtil.getPages(query, request, RateLimitRule.class, template);
    }

    @Override
    public Mono<Long> findCount(RateLimitRule rateLimitRule) {
        Query query = getQuery(rateLimitRule);
        return template.count(query, RateLimitRule.class);
    }

    @Override
    public Mono<RateLimitRule> create(RateLimitRule rateLimitRule) {
        rateLimitRule.setCreateTime(DateHelper.getSystemTime(DateHelper.DEFAULT_DATETIME_FORMAT));
        return rateLimitRuleMapper.insert(rateLimitRule)
                .doOnSuccess(r -> routeEnhanceCacheService.saveRateLimitRule(r));
    }

    @Override
    public Mono<RateLimitRule> update(RateLimitRule rateLimitRule) {
        return this.rateLimitRuleMapper.findById(rateLimitRule.getId())
                .flatMap(r -> {
                    routeEnhanceCacheService.removeRateLimitRule(r);
                    BeanUtils.copyProperties(rateLimitRule, r);
                    return this.rateLimitRuleMapper.save(r);
                }).doOnSuccess(r -> routeEnhanceCacheService.saveRateLimitRule(r));
    }

    @Override
    public Flux<RateLimitRule> delete(String ids) {
        String[] idArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(ids, ",");
        return rateLimitRuleMapper.deleteByIdIn(idArray)
                .doOnNext(r -> routeEnhanceCacheService.removeRateLimitRule(r));
    }

    private Query getQuery(RateLimitRule rateLimitRule) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (StringUtils.isNotBlank(rateLimitRule.getRequestMethod())) {
            criteria.and("requestMethod").is(rateLimitRule.getRequestMethod());
        }
        if (StringUtils.isNotBlank(rateLimitRule.getRequestUri())) {
            criteria.and("requestUri").is(rateLimitRule.getRequestUri());
        }
        if (StringUtils.isNotBlank(rateLimitRule.getStatus())) {
            criteria.and("status").is(rateLimitRule.getStatus());
        }
        query.addCriteria(criteria);
        return query;
    }
}
