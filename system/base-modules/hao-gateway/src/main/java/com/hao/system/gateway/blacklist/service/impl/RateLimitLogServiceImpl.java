package com.hao.system.gateway.blacklist.service.impl;

import com.hao.common.utils.AddressUtil;
import com.hao.common.utils.DateHelper;
import com.hao.common.utils.vo.QueryRequest;
import com.hao.system.gateway.blacklist.entity.RateLimitLog;
import com.hao.system.gateway.blacklist.mapper.RateLimitLogMapper;
import com.hao.system.gateway.blacklist.service.RateLimitLogService;
import com.hao.system.gateway.blacklist.utils.PageableExecutionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class RateLimitLogServiceImpl implements RateLimitLogService {

    @Autowired
    private RateLimitLogMapper rateLimitLogMapper;
    @Autowired
    private ReactiveMongoTemplate template;

    @Override
    public Mono<RateLimitLog> create(RateLimitLog rateLimitLog) {
        rateLimitLog.setCreateTime(DateHelper.getSystemTime(DateHelper.DEFAULT_DATETIME_FORMAT));
        rateLimitLog.setLocation(AddressUtil.getCityInfo(rateLimitLog.getIp()));
        return rateLimitLogMapper.insert(rateLimitLog);
    }

    @Override
    public Flux<RateLimitLog> delete(String ids) {
        String[] idArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(ids, ",");
        return rateLimitLogMapper.deleteByIdIn(idArray);
    }

    @Override
    public Flux<RateLimitLog> findPages(QueryRequest request, RateLimitLog rateLimitLog) {
        Query query = getQuery(rateLimitLog);
        return PageableExecutionUtil.getPages(query, request, RateLimitLog.class, template);
    }

    @Override
    public Mono<Long> findCount(RateLimitLog rateLimitLog) {
        Query query = getQuery(rateLimitLog);
        return template.count(query, RateLimitLog.class);
    }

    private Query getQuery(RateLimitLog rateLimitLog) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (StringUtils.isNotBlank(rateLimitLog.getIp())) {
            criteria.and("ip").is(rateLimitLog.getIp());
        }
        if (StringUtils.isNotBlank(rateLimitLog.getRequestMethod())) {
            criteria.and("requestMethod").is(rateLimitLog.getRequestMethod());
        }
        if (StringUtils.isNotBlank(rateLimitLog.getRequestUri())) {
            criteria.and("requestUri").is(rateLimitLog.getRequestUri());
        }
        if (StringUtils.isNotBlank(rateLimitLog.getCreateTimeFrom())
                && StringUtils.isNotBlank(rateLimitLog.getCreateTimeTo())) {
            criteria.andOperator(
                    Criteria.where("createTime").gt(rateLimitLog.getCreateTimeFrom()),
                    Criteria.where("createTime").lt(rateLimitLog.getCreateTimeTo())
            );
        }
        query.addCriteria(criteria);
        return query;
    }
}
