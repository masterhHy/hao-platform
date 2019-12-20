package com.hao.system.finance.data.service.db;


import com.hao.entity.finance.FundRanking;
import com.hao.system.finance.data.common.BactchInserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class FundCodePipeline implements Pipeline {
    @Resource
    private BactchInserMapper bactchInserMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<FundRanking> codeList = resultItems.get("data");
        bactchInserMapper.bacthInsert(codeList);
    }

}
