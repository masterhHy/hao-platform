package com.hao.system.finance.data.service.db;


import com.hao.entity.finance.StockCode;
import com.hao.system.finance.data.common.BactchInserMapper;
import com.hao.system.finance.data.mapper.StockCodeMapper;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class StockCodeInvestorPipeline implements Pipeline {
    @Resource
    private BactchInserMapper bactchInserMapper;
    @Resource
    private StockCodeMapper stockCodeMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {


    }

}
