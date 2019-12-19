package com.hao.system.finance.data.service.db;


import com.hao.system.finance.data.common.BactchInserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;

@Component
@Slf4j
public class FundCodeRankingsPipeline implements Pipeline {
    @Resource
    private BactchInserMapper bactchInserMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {


    }

}
